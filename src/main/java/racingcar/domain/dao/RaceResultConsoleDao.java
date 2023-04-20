package racingcar.domain.dao;

import racingcar.domain.dao.entity.RaceEntity;

import java.util.Collections;
import java.util.List;

public class RaceResultConsoleDao implements RaceResultDao {
    @Override
    public Long save(final RaceEntity raceEntity) {
        return null;
    }

    @Override
    public List<RaceEntity> findAll() {
        return Collections.emptyList();
    }
}
