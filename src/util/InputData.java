package util;

import exceptions.InputOutOfRangeException;
import service.Player;

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
                cleanBuffer();
            }
        }
        return numberOfPlayers;
    }

    public int numberOfPin() {
        return scanner.nextInt();
    }

    public int resultOfFirst(Player player) {
        int first;
        while (true) {
            try {
                Message.askFirst(player);
                first = scanner.nextInt();
                if (0 > first || first > 10) { // First 입력 오류
                    throw new InputOutOfRangeException("투구 결과는 0 ~ 10 사이의 값만 입력 가능합니다.");
                }
                return first;
            } catch (InputOutOfRangeException e) {
                Message.InputOutOfRangeExceptionPrint(e.getMessage());
            } catch (InputMismatchException e) {
                Message.InputMismatchExceptionPrint();
                cleanBuffer(); // 버퍼를 비워 주지 않으면 무한 반복이 일어 난다고 함...
            }
        }
    }

    public int resultOfSpare(Player player, int first) {
        int spare;
        while (true) {
            try {
                Message.askSpare(player);
                spare = scanner.nextInt();

                if (first == 10 && (0 > spare || spare > 10)) { // 10 Frame 인 경우
                    throw new InputOutOfRangeException("스페어 결과는 0 ~ 10 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                }
                if (first < 10 && (0 > spare || spare > 10 || first + spare > 10)) { // 10 Frame 인 경우
                    throw new InputOutOfRangeException("스페어 결과는 0 ~ " + (10 - first) + " 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                }
                return spare;
            } catch (InputOutOfRangeException e) {
                Message.InputOutOfRangeExceptionPrint(e.getMessage());
            } catch (InputMismatchException e) {
                Message.InputMismatchExceptionPrint();
                cleanBuffer(); // 버퍼를 비워 주지 않으면 무한 반복이 일어 난다고 함...
            }
        }
    }

    public int resultOfBonus(Player player, int first, int spare) {
        int bonus;
        while (true) {
            try {
                Message.askBonus(player);
                bonus = scanner.nextInt();

                if (first == 10 && spare < 10 && (0 > bonus || bonus > 10 || spare + bonus > 10)) { // Bonus 입력 오류
                    throw new InputOutOfRangeException("보너스 결과는 0 ~ " + (10 - spare) + " 사이의 값만 입력 가능합니다. \n재입력 바랍니다.");
                }

                return bonus;
            } catch (InputOutOfRangeException e) {
                Message.InputOutOfRangeExceptionPrint(e.getMessage());
            } catch (InputMismatchException e) {
                Message.InputMismatchExceptionPrint();
                cleanBuffer(); // 버퍼를 비워 주지 않으면 무한 반복이 일어 난다고 함...
            }
        }
    }

    public void cleanBuffer() {
        scanner.nextLine();
    }

}
