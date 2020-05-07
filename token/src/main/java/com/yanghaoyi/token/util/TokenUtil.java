package com.yanghaoyi.token.util;


import com.yanghaoyi.token.exception.TokenException;
import com.yanghaoyi.token.util.constants.ErrCodeConstant;
import io.jsonwebtoken.Claims;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
public class TokenUtil {


    public static int getTokenUserId() {
        String token = getRequest().getHeader("x-access-token");// 从 http 请求头中取出 token
        if (null == token) {
            throw new TokenException(ErrCodeConstant.NO_TOKEN, "Token 缺失");
        }
        Claims c = null;
        try {
            c = JwtUtil.parseJWT(token);
        } catch (Exception e) {
              e.printStackTrace();
            throw new TokenException(ErrCodeConstant.ERROR_TOKEN, "Token 错误");
        }
        if (null == c) {
            throw new TokenException(ErrCodeConstant.ERROR_TOKEN, "Token 错误");
        }
        int userId = c.get(JwtUtil.keyUserId,Integer.class);
        return userId;
    }


    public static boolean verifyToken(){
//        int userId = getTokenUserId();
        return true;
    }

    /**
     * 获取request
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }
}

/**
 * https://blog.csdn.net/baidu_41881054/article/details/91991539?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-1
 * https://blog.csdn.net/east123321/article/details/79799287
 *
 * **/
