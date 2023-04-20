package racingcar.domain.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import racingcar.domain.Car;
import racingcar.domain.CarName;
import racingcar.domain.CarPosition;
import racingcar.domain.dao.entity.CarEntity;
import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class CarJdbcDaoTest {

    private static final List<Car> cars = List.of(new Car(CarName.create("test1"),
            CarPosition.init()), new Car(CarName.create("test2"), CarPosition.init()));
    private static final int trialCount = 10;
    private static final String winners = "test1,test2";

    @Autowired
    private CarJdbcDao carJdbcDao;
    @Autowired
    private RaceResultJdbcDao raceResultJdbcDao;

    @Test
    @DisplayName("차들의 정보를 저장한다")
    public void saveAll() {
        //given
        final Long raceResultId = raceResultJdbcDao.save(RaceEntity.of(trialCount, winners));
        final List<CarEntity> carEntities = createCarEntities(raceResultId);

        //when
        carJdbcDao.saveAll(carEntities);

        // then
        final List<CarEntity> result = carJdbcDao.findAll();
        final List<String> resultNames = result.stream().map(CarEntity::getName)
                .collect(Collectors.toUnmodifiableList());

        // then
        assertThat(resultNames)
                .isEqualTo(List.of("test1", "test2"));
    }

    @Test
    @DisplayName("모든 차 리스트를 반환한다")
    public void findAll() {
        // given
        final Long raceResultId = raceResultJdbcDao.save(RaceEntity.of(trialCount, winners));
        carJdbcDao.saveAll(createCarEntities(raceResultId));

        // when
        final List<CarEntity> result = carJdbcDao.findAll();
        final List<String> resultNames = result.stream().map(CarEntity::getName)
                .collect(Collectors.toUnmodifiableList());

        // then
        assertThat(resultNames)
                .isEqualTo(List.of("test1", "test2"));
    }

    private List<CarEntity> createCarEntities(final Long raceResultId) {
        return cars.stream()
                .map(car -> CarEntity.of(car.getName(), car.getPosition(), raceResultId))
                .collect(Collectors.toUnmodifiableList());
    }


}
