package com.potopalskyi.movieland.entity.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoDataFoundException(String message) {
        super(message);
    }
}
