package racingcar.controller;

import racingcar.dto.CarResponse;
import racingcar.dto.RaceRequest;
import racingcar.dto.RaceResponse;
import racingcar.service.RaceService;
import racingcar.view.InputView;
import racingcar.view.OutputView;

import java.util.List;

public class RaceConsoleController {

    private final RaceService raceService;

    public RaceConsoleController(final RaceService raceService) {
        this.raceService = raceService;
    }

    public void race() {
        final String carNames = getCarNames();
        final int tryCount = getTryCount();
        final RaceRequest raceRequest = new RaceRequest(carNames, tryCount);
        final RaceResponse raceResponse = raceService.play(raceRequest);
        printRaceResult(raceResponse);
    }

    private String getCarNames() {
        return InputView.getUserInput(InputView::getCarNames);
    }

    private static int getTryCount() {
        return InputView.getUserInput(InputView::getTryCount);
    }

    private void printRaceResult(final RaceResponse raceResponse) {
        final String winners = raceResponse.getWinners();
        OutputView.printWinnersResult(winners);
        final List<CarResponse> racingCars = raceResponse.getRacingCars();
        racingCars
                .forEach(car -> OutputView.printCarStatus(car.getName(), car.getPosition()));
    }
}
