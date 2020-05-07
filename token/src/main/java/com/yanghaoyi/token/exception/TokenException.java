package com.yanghaoyi.token.exception;

import com.yanghaoyi.exception.exception.HaoyiRuntimeException;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public class TokenException extends HaoyiRuntimeException {

    public TokenException() {
        super(5000, String.format("[%s]", new Object[]{"服务端异常"}));
    }

    public TokenException(int code) {
        super(code, String.format("[%s]", new Object[]{"服务端异常"}));
    }

    public TokenException(String description) {
        super(5000, String.format("%s", new Object[]{description}));
    }

    public TokenException(int code, String description) {
        super(code, String.format("%s", new Object[]{description}));
    }

    public TokenException(String description, Throwable cause) {
        super(String.format("%s", new Object[]{description}), cause);
    }

    public TokenException(int code, String description, Throwable cause) {
        super(code, String.format("%s", new Object[]{description}), cause);
    }

}
