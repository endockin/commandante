package com.endockin.commandante.service.scheduler;

public class SchedulerServiceException extends Exception {

    public enum Type {

        NOT_FOUND("Not Found"), ALREADY_EXISTS("Already Exists"), OTHER("Other");

        private final String message;

        private Type(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "Type{" + "message=" + message + '}';
        }

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
