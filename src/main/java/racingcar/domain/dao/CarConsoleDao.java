package racingcar.domain.dao;

import racingcar.domain.dao.entity.CarEntity;

import java.util.Collections;
import java.util.List;

public class CarConsoleDao implements CarDao {
    @Override
    public void saveAll(final List<CarEntity> carEntities) {

    }

    @Override
    public List<CarEntity> findAll() {
        return Collections.emptyList();
    }
}
