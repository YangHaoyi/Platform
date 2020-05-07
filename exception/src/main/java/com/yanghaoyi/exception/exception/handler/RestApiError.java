package com.yanghaoyi.exception.exception.handler;

import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public class RestApiError {
    private int code;
    private String description;
    private List tips;
    private long lastUpdateTime;

    private RestApiError() {
        this.lastUpdateTime = 0L;
        this.lastUpdateTime = System.currentTimeMillis();
    }

    public RestApiError(Throwable ex) {
        this();
        this.description = ex.getLocalizedMessage();
    }

    public RestApiError(String description, Throwable ex) {
        this();
        this.description = description;
    }

    private void addSubError(ApiSubError subError) {
        if(this.tips == null) {
            this.tips = new ArrayList();
        }

        this.tips.add(subError);
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> constraintViolations) {
        constraintViolations.forEach(this::addValidationError);
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    private void addValidationError(String object, String field, Object rejectedValue, String message) {
        this.addSubError(new RestApiValidationError(object, field, rejectedValue, message));
    }

    private void addValidationError(String object, String message) {
        this.addSubError(new RestApiValidationError(object, message));
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(fieldError.getObjectName(), fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    private void addValidationError(ConstraintViolation<?> cv) {
        this.addValidationError(cv.getRootBeanClass().getSimpleName(), ((PathImpl)cv.getPropertyPath()).getLeafNode().asString(), cv.getInvalidValue(), cv.getMessage());
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public List getTips() {
        return this.tips;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTips(List tips) {
        this.tips = tips;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof RestApiError)) {
            return false;
        } else {
            RestApiError other = (RestApiError)o;
            if(!other.canEqual(this)) {
                return false;
            } else if(this.getCode() != other.getCode()) {
                return false;
            } else {
                label41: {
                    Object this$description = this.getDescription();
                    Object other$description = other.getDescription();
                    if(this$description == null) {
                        if(other$description == null) {
                            break label41;
                        }
                    } else if(this$description.equals(other$description)) {
                        break label41;
                    }

                    return false;
                }

                Object this$tips = this.getTips();
                Object other$tips = other.getTips();
                if(this$tips == null) {
                    if(other$tips != null) {
                        return false;
                    }
                } else if(!this$tips.equals(other$tips)) {
                    return false;
                }

                if(this.getLastUpdateTime() != other.getLastUpdateTime()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof RestApiError;
    }

//    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        int result = result * 59 + this.getCode();
//        Object $description = this.getDescription();
//        result = result * 59 + ($description == null?43:$description.hashCode());
//        Object $tips = this.getTips();
//        result = result * 59 + ($tips == null?43:$tips.hashCode());
//        long $lastUpdateTime = this.getLastUpdateTime();
//        result = result * 59 + (int)($lastUpdateTime >>> 32 ^ $lastUpdateTime);
//        return result;
//    }

    public String toString() {
        return "RestApiError(code=" + this.getCode() + ", description=" + this.getDescription() + ", tips=" + this.getTips() + ", lastUpdateTime=" + this.getLastUpdateTime() + ")";
    }
}
