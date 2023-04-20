package racingcar;

import racingcar.controller.RaceConsoleController;
import racingcar.domain.RaceNumberGenerator;
import racingcar.domain.dao.CarConsoleDao;
import racingcar.domain.dao.RaceResultConsoleDao;
import racingcar.service.RaceService;

public class ConsoleApplication {
    public static void main(String[] args) {
        final RaceConsoleController raceConsoleController = new RaceConsoleController(
                new RaceService(new RaceNumberGenerator(), new CarConsoleDao(), new RaceResultConsoleDao()));
        raceConsoleController.race();
    }
}
