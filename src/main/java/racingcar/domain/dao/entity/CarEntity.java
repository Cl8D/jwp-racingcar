package racingcar.domain.dao.entity;

public class CarEntity {

    private Long carId;
    private final String name;
    private final int position;
    private final Long raceResultId;

    private CarEntity(final String name, final int position, final Long raceResultId) {
        this.name = name;
        this.position = position;
        this.raceResultId = raceResultId;
    }

    public CarEntity(final Long carId, final String name, final int position, final Long raceResultId) {
        this.carId = carId;
        this.name = name;
        this.position = position;
        this.raceResultId = raceResultId;
    }

    public static CarEntity of(final String name, final int position, final Long raceResultId) {
        return new CarEntity(name, position, raceResultId);
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
