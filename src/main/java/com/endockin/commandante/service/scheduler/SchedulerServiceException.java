package com.endockin.commandante.service.scheduler;

public class SchedulerServiceException extends Exception {

    public enum Type {
        NOT_FOUND, ALREADY_EXISTS, OTHER
    }

    private final Type type;

    public SchedulerServiceException(String message, Type type) {
        super(message);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

}
