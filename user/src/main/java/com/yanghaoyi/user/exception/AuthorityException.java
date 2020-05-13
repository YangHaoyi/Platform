package com.yanghaoyi.user.exception;

import com.yanghaoyi.exception.exception.HaoyiRuntimeException;

/**
 * @author : YangHaoYi on 2020/5/13.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/13.
 * Version : V 1.0
 */
public class AuthorityException extends HaoyiRuntimeException {
    public AuthorityException(int code) {
        super(code);
    }

    public AuthorityException(String msg) {
        super(msg);
    }

    public AuthorityException(int code, String description) {
        super(code, description);
    }

    public AuthorityException(int code, String description, String msg) {
        super(code, description, msg);
    }

    public AuthorityException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthorityException(int code, String description, Throwable cause) {
        super(code, description, cause);
    }
}
