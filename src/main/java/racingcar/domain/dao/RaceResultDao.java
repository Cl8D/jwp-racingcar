package racingcar.domain.dao;

import racingcar.domain.dao.entity.RaceEntity;

import java.util.List;

public interface RaceResultDao {
    Long save(final RaceEntity raceEntity);

    List<RaceEntity> findAll();
}
