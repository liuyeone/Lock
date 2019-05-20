package com.example.lock.exception;

public class ObjectOptimisticLockingFailureException extends RuntimeException {

    String code;

    String message;

    public ObjectOptimisticLockingFailureException() {
    }

    public ObjectOptimisticLockingFailureException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
