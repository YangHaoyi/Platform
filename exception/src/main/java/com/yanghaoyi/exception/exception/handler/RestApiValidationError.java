package com.yanghaoyi.exception.exception.handler;

import java.beans.ConstructorProperties;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public class RestApiValidationError implements ApiSubError{
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    public RestApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }

    public String getObject() {
        return this.object;
    }

    public String getField() {
        return this.field;
    }

    public Object getRejectedValue() {
        return this.rejectedValue;
    }

    public String getMessage() {
        return this.message;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return "RestApiValidationError(object=" + this.getObject() + ", field=" + this.getField() + ", rejectedValue=" + this.getRejectedValue() + ", message=" + this.getMessage() + ")";
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof RestApiValidationError)) {
            return false;
        } else {
            RestApiValidationError other = (RestApiValidationError)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$object = this.getObject();
                    Object other$object = other.getObject();
                    if(this$object == null) {
                        if(other$object == null) {
                            break label59;
                        }
                    } else if(this$object.equals(other$object)) {
                        break label59;
                    }

                    return false;
                }

                Object this$field = this.getField();
                Object other$field = other.getField();
                if(this$field == null) {
                    if(other$field != null) {
                        return false;
                    }
                } else if(!this$field.equals(other$field)) {
                    return false;
                }

                Object this$rejectedValue = this.getRejectedValue();
                Object other$rejectedValue = other.getRejectedValue();
                if(this$rejectedValue == null) {
                    if(other$rejectedValue != null) {
                        return false;
                    }
                } else if(!this$rejectedValue.equals(other$rejectedValue)) {
                    return false;
                }

                Object this$message = this.getMessage();
                Object other$message = other.getMessage();
                if(this$message == null) {
                    if(other$message != null) {
                        return false;
                    }
                } else if(!this$message.equals(other$message)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestApiValidationError;
    }

//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $object = this.getObject();
//        int result = result * 59 + ($object == null?43:$object.hashCode());
//        Object $field = this.getField();
//        result = result * 59 + ($field == null?43:$field.hashCode());
//        Object $rejectedValue = this.getRejectedValue();
//        result = result * 59 + ($rejectedValue == null?43:$rejectedValue.hashCode());
//        Object $message = this.getMessage();
//        result = result * 59 + ($message == null?43:$message.hashCode());
//        return result;
//    }

    @ConstructorProperties({"object", "field", "rejectedValue", "message"})
    public RestApiValidationError(String object, String field, Object rejectedValue, String message) {
        this.object = object;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }
}
