package com.yanghaoyi.token.exception;

import com.yanghaoyi.exception.exception.HaoyiRuntimeException;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public class RegisterException extends HaoyiRuntimeException {

    public RegisterException() {
        super(5000, String.format("[%s]", new Object[]{"服务端异常"}));
    }

    public RegisterException(int code) {
        super(code, String.format("[%s]", new Object[]{"服务端异常"}));
    }

    public RegisterException(String description) {
        super(5000, String.format("%s", new Object[]{description}));
    }

    public RegisterException(int code, String description) {
        super(code, String.format("%s", new Object[]{description}));
    }

    public RegisterException(String description, Throwable cause) {
        super(String.format("%s", new Object[]{description}), cause);
    }

    public RegisterException(int code, String description, Throwable cause) {
        super(code, String.format("%s", new Object[]{description}), cause);
    }

}
