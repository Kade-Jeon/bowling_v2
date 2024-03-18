package util;

import exceptions.InputOutOfRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputData {

    private final Scanner scanner = new Scanner(System.in);

    public int numberOfPlayers() {
        int numberOfPlayers;
        while (true) {
            Message.askNumberOfPlayer();

            try {
                numberOfPlayers = scanner.nextInt();
                if (1 > numberOfPlayers || numberOfPlayers > 4) { // 참가자 수 입력 오류
                    throw new InputOutOfRangeException("참가자 수는 1 ~ 4 사이의 값만 입력 가능합니다.");
                }
                break;
            } catch (InputOutOfRangeException e) {
                Message.InputOutOfRangeExceptionPrint(e.getMessage());
            } catch (InputMismatchException e) {
                Message.InputMismatchExceptionPrint();
                scanner.nextLine();
            }
        }
        return numberOfPlayers;
    }

    public int numberOfPin() {
        return scanner.nextInt();
    }

    public void cleanBuffer() {
        scanner.nextLine();
    }
}
