package racingcar.domain.dao.entity;

public class CarEntity {

    private final Long carId;
    private final String name;
    private final int position;
    private final Long raceResultId;

    public CarEntity(final Long carId, final String name, final int position, final Long raceResultId) {
        this.carId = carId;
        this.name = name;
        this.position = position;
        this.raceResultId = raceResultId;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public Long getRaceResultId() {
        return raceResultId;
    }
}
