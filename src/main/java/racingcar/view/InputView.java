package racingcar.view;

import racingcar.common.exception.DuplicateResourceException;
import racingcar.common.exception.ResourceLengthException;

import java.util.Scanner;
import java.util.function.Supplier;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static <T> T getUserInput(Supplier<T> inputReader) {
        try {
            return inputReader.get();
        } catch (DuplicateResourceException e) {
            OutputView.printMessage("중복된 값을 입력할 수 없습니다.");
        } catch (ResourceLengthException e) {
            OutputView.printMessage(String.format("최대 %d글자까지 입력할 수 있습니다.", e.getLength().getData()));
        } catch (NumberFormatException e) {
            OutputView.printMessage("숫자를 입력해 주세요.");
        } catch (IllegalArgumentException e) {
            OutputView.printMessage(e.getMessage());
        }
        return getUserInput(inputReader);
    }

    public static String getCarNames() {
        return getUserInput(() -> {
            OutputView.printMessage("경주할 자동차 이름을 입력하세요(이름은 쉼표(,)를 기준으로 구분).");
            return readConsole();
        });
    }

    public static int getTryCount() {
        return getUserInput(() -> {
            OutputView.printMessage("시도할 회수는 몇회인가요?");
            return Integer.parseInt(readConsole());
        });
    }

    private static String readConsole() {
        final String command = scanner.nextLine();
        if (command == null || command.isBlank()) {
            throw new IllegalArgumentException("빈 값을 입력할 수 없습니다.");
        }
        return command;
    }
}
