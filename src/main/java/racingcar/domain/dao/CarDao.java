package racingcar.domain.dao;

import racingcar.domain.dao.entity.CarEntity;

import java.util.List;

public interface CarDao {
    void saveAll(final List<CarEntity> carEntities);

    List<CarEntity> findAll();
}
