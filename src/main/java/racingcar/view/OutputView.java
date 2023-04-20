package racingcar.view;

public class OutputView {

    private static final String WINNER_MESSAGE_FORMAT = "우승자: %s";
    private static final String CAR_MESSAGE_FORMAT = "[이름]: %s - [이동거리]: %d";

    public static void printMessage(final String message) {
        System.out.println(message);
    }

    public static void printWinnersResult(final String winners) {
        printMessage(String.format(WINNER_MESSAGE_FORMAT, winners));
    }

    public static void printCarStatus(final String name, final int position) {
        printMessage(String.format(CAR_MESSAGE_FORMAT, name, position));
    }
}
