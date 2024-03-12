package Exceptions;

import java.util.InputMismatchException;

public class InputOutOfRangeException extends InputMismatchException {
    public InputOutOfRangeException(String s) {
        super(s);
    }
}
