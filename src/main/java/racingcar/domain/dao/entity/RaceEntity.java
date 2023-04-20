package racingcar.domain.dao.entity;

public class RaceEntity {

    private Long id;
    private final int trialCount;
    private final String winners;

    private RaceEntity(final int trialCount, final String winners) {
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public RaceEntity(Long id, int trialCount, String winners) {
        this.id = id;
        this.trialCount = trialCount;
        this.winners = winners;
    }

    public static RaceEntity of(final int count, final String winners) {
        return new RaceEntity(count, winners);
    }

    public Long getId() {
        return id;
    }

    public int getTrialCount() {
        return trialCount;
    }

    public String getWinners() {
        return winners;
    }
}
