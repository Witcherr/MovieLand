package com.potopalskyi.movieland.entity.exception;

public class AlterIntoDBException extends RuntimeException{

    public AlterIntoDBException(String message, Throwable cause){
        super(message, cause);
    }
}
