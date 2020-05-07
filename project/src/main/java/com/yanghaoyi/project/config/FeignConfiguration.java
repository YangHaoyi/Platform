package com.yanghaoyi.project.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {
//    private static final Logger logger = LoggerFactory.getLogger(FeignConfiguration.class);

    @Override
    public void apply(RequestTemplate template) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(null != attributes) {
            HttpServletRequest request = attributes.getRequest();
            String token = request.getHeader("x-access-token");
            template.header("x-access-token", token);
        }
//        logger.info("feign interceptor header:{}",template);
    }
}
