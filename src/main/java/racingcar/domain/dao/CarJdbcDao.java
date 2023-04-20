package racingcar.domain.dao;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import racingcar.domain.dao.entity.CarEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CarJdbcDao implements CarDao {

    private final JdbcTemplate jdbcTemplate;

    public CarJdbcDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void saveAll(final List<CarEntity> carEntities) {
        final String query = "INSERT INTO car (name, position, race_result_id) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(final PreparedStatement ps, final int i) throws SQLException {
                final CarEntity carEntity = carEntities.get(i);
                ps.setString(1, carEntity.getName());
                ps.setInt(2, carEntity.getPosition());
                ps.setLong(3, carEntity.getRaceResultId());
            }

            @Override
            public int getBatchSize() {
                return carEntities.size();
            }
        });
    }

    @Override
    public List<CarEntity> findAll() {
        final String query = "SELECT * FROM car";
        return jdbcTemplate.query(query, (result, count) ->
                new CarEntity(result.getLong("car_id"), result.getString("name"),
                        result.getInt("position"), result.getLong("race_result_id")));
    }
}
