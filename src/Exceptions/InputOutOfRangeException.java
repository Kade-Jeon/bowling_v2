package Exceptions;

import java.util.InputMismatchException;

public class InputOutOfRangeException extends InputMismatchException {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";

    public InputOutOfRangeException(String s) {
        super(ANSI_RED + s + ANSI_RESET);
    }
}
