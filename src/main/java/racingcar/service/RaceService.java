package racingcar.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Cars;
import racingcar.domain.NumberGenerator;
import racingcar.domain.Race;
import racingcar.domain.dao.CarDao;
import racingcar.domain.dao.RaceResultDao;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;
import racingcar.dto.CarResponse;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class RaceService {

    private final NumberGenerator numberGenerator;
    private final CarDao carDao;
    private final RaceResultDao raceResultDao;

    public RaceService(final NumberGenerator numberGenerator, final CarDao carDao, final RaceResultDao raceResultDao) {
        this.numberGenerator = numberGenerator;
        this.carDao = carDao;
        this.raceResultDao = raceResultDao;
    }

    @Transactional
    public RaceResponse play(final RaceRequest raceRequest) {
        final List<String> names = raceRequest.makeSplitNames();
        final Cars cars = Cars.create(names, numberGenerator);
        final Race race = new Race(raceRequest.getCount());

        race.run(cars);
        final RaceResponse raceResponse = makeRaceResponse(cars);
        final RaceEntity raceEntity = RaceEntity.of(raceRequest.getCount(), raceResponse.getWinners());
        final Long raceResultId = raceResultDao.save(raceEntity);
        final List<CarEntity> carEntities = createCarEntities(cars, raceResultId);
        carDao.saveAll(carEntities);
        return raceResponse;
    }

    public List<RaceResponse> getRaceResult() {
        final List<RaceEntity> raceEntities = raceResultDao.findAll();
        final List<CarEntity> carEntities = carDao.findAll();
        final Map<Long, List<CarEntity>> carEntitiesByRaceResultId = carEntities.stream()
                .collect(Collectors.groupingBy(CarEntity::getRaceResultId));

        return raceEntities.stream()
                .map(raceEntity -> {
                    final List<CarEntity> targetCarEntities = carEntitiesByRaceResultId.get(raceEntity.getId());
                    final List<CarResponse> carResponses = makeCarRaceResult(targetCarEntities);
                    return RaceResponse.create(raceEntity.getWinners(), carResponses);
                })
                .collect(Collectors.toUnmodifiableList());
    }

    private RaceResponse makeRaceResponse(final Cars cars) {
        final List<String> winners = cars.getWinnerCarNames();
        final List<CarResponse> carRaceResult = makeCarRaceResult(cars);
        return RaceResponse.create(winners, carRaceResult);
    }

    private List<CarResponse> makeCarRaceResult(final Cars cars) {
        return cars.getCars()
                .stream()
                .map(car -> new CarResponse(car.getName(), car.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<CarEntity> createCarEntities(final Cars cars, final Long raceResultId) {
        return cars.getCars().stream()
                .map(car -> CarEntity.of(car.getName(), car.getPosition(), raceResultId))
                .collect(Collectors.toUnmodifiableList());
    }

    private List<CarResponse> makeCarRaceResult(final List<CarEntity> carEntities) {
        return carEntities.stream()
                .map(carEntity -> new CarResponse(carEntity.getName(), carEntity.getPosition()))
                .collect(Collectors.toUnmodifiableList());
    }
}
