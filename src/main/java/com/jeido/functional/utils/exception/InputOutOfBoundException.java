package com.jeido.functional.utils.exception;

public class InputOutOfBoundException extends RuntimeException {
    public InputOutOfBoundException(int in, int size) {
        this(in, 0, size);
    }

    public InputOutOfBoundException(int in, int min, int max) {
      super(String.format("Input '%d' out of bound [%d-%d]!", in, min, max));
    }
}
