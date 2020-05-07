package com.yanghaoyi.exception.exception;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public abstract class HaoyiRuntimeException extends RuntimeException{
    private int code;
    private String description;

    public HaoyiRuntimeException(int code) {
        this.code = code;
    }

    public HaoyiRuntimeException(String msg) {
        super(msg);
    }

    public HaoyiRuntimeException(int code, String description) {
        super(description);
        this.code = code;
        this.description = description;
    }

    public HaoyiRuntimeException(int code, String description, String msg) {
        super(msg);
        this.code = code;
        this.description = description;
    }

    public HaoyiRuntimeException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public HaoyiRuntimeException(int code, String description, Throwable cause) {
        super(description, cause);
        this.code = code;
        this.description = description;
    }

    public String getMessage() {
        return this.buildMessage(super.getMessage(), (Throwable)null);
    }

    public String getMessageCause() {
        return this.buildMessage(super.getMessage(), this.getCause());
    }

    public String getDescription() {
        return this.description;
    }

    public int getCode() {
        return this.code;
    }

    public Throwable getRootCause() {
        Throwable rootCause = null;

        for(Throwable cause = this.getCause(); cause != null && cause != rootCause; cause = cause.getCause()) {
            rootCause = cause;
        }

        return rootCause;
    }

    public Throwable getMostSpecificCause() {
        Throwable rootCause = this.getRootCause();
        return (Throwable)(rootCause != null?rootCause:this);
    }

    public boolean contains(Class<?> exType) {
        if(exType == null) {
            return false;
        } else if(exType.isInstance(this)) {
            return true;
        } else {
            Throwable cause = this.getCause();
            if(cause == this) {
                return false;
            } else if(cause instanceof HaoyiRuntimeException) {
                return ((HaoyiRuntimeException)cause).contains(exType);
            } else {
                while(cause != null) {
                    if(exType.isInstance(cause)) {
                        return true;
                    }

                    if(cause.getCause() == cause) {
                        break;
                    }

                    cause = cause.getCause();
                }

                return false;
            }
        }
    }

    private String buildMessage(String message, Throwable cause) {
        if(cause != null) {
            StringBuilder sb = new StringBuilder();
            if(message != null) {
                sb.append(message).append("; ");
            }

            sb.append("nested exception is ").append(cause);
            return sb.toString();
        } else {
            return message;
        }
    }
}
