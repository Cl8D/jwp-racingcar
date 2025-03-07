package racingcar.domain.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import racingcar.domain.dao.entity.RaceEntity;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class RaceResultJdbcDao implements RaceResultDao {

    private final JdbcTemplate jdbcTemplate;

    public RaceResultJdbcDao(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Long save(final RaceEntity raceEntity) {
        final String query = "INSERT INTO race_result (trial_count, winners, created_at) VALUES (?, ?, ?)";
        final KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            final PreparedStatement preparedStatement = con.prepareStatement(query,
                    new String[]{"race_result_id"});
            preparedStatement.setString(1, String.valueOf(raceEntity.getTrialCount()));
            preparedStatement.setString(2, raceEntity.getWinners());
            preparedStatement.setString(3, LocalDateTime.now().toString());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<RaceEntity> findAll() {
        final String query = "SELECT * FROM race_result";
        return jdbcTemplate.query(query, (result, count) -> new RaceEntity(
                result.getLong("race_result_id"),
                result.getInt("trial_count"),
                result.getString("winners")
        ));
    }
}
