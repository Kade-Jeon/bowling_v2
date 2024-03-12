package Util;

import Exceptions.InputOutOfRangeException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputData {

    private final Scanner scanner = new Scanner(System.in);

    public int numberOfPlayers() {
        int numberOfPlayers;
        while (true) {
            Message.askPeople();
            try {
                numberOfPlayers = scanner.nextInt();
                if (1 > numberOfPlayers || numberOfPlayers > 4) { // First 입력 오류
                    throw new InputOutOfRangeException("참가자 수는 1 ~ 4 사이의 값만 입력 가능합니다.");
                }
                break;
            } catch (InputOutOfRangeException e) {
                System.out.println(e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("숫자만 입력 가능합니다.");
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
