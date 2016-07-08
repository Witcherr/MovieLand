package com.potopalskyi.movieland.entity.exception;

public class NoDataFoundException extends RuntimeException {

    public NoDataFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
