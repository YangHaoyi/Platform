package com.yanghaoyi.exception.exception.handler;


import com.yanghaoyi.exception.exception.HaoyiRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * Created by 浩艺 on 2020/4/27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Locale DEFAULT_LOCALE;

    @Autowired
    private MessageSource messageSource;

    @ResponseBody
    @ExceptionHandler({HaoyiRuntimeException.class})
    public RestApiError handleException(HttpServletRequest request, HaoyiRuntimeException ex) {
        RestApiError apiError = new RestApiError(this.getErrorMessage(ex.getDescription()), ex);
        apiError.setCode(ex.getCode());
//        log.error("path:{} 堆栈信息:{}", request.getRequestURL().toString(), ex);
        System.out.println("=====================================>");
        return apiError;
    }

    protected String getErrorMessage(String key) {
        try {
            return this.messageSource.getMessage(key, (Object[])null, DEFAULT_LOCALE);
        } catch (Exception var3) {
            return key;
        }
    }

    static {
        DEFAULT_LOCALE = Locale.SIMPLIFIED_CHINESE;
    }
}
