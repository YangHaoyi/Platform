package com.yanghaoyi.getway.interceptor;

import com.yanghaoyi.token.util.TokenUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
public class AuthorizationInterceptor implements HandlerInterceptor {


    /**
     * @return false 请求终止
     *          true  请求向下执行
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
//        ResponseInfo responseInfo = new ResponseInfo(ResponseConfig.noLoginCode, ResponseConfig.noLoginMsg);
//        try{
//            String token = request.getHeader("user-agent");
//            if(token != null && token.length() > 0){
//                Claims c = JwtUtil.parseJWT(token);
//                int userId = c.get(JwtUtil.keyUserId,Integer.class);
////                String userId2 = redisTemplate.opsForValue().get(token);
//                return true;
//            }
//        }catch (ExpiredJwtException e){
//            responseInfo = new ResponseInfo(ResponseConfig.tokenValidCode, ResponseConfig.tokenValidMsg);
//        }catch (Exception e){
//            responseInfo = new ResponseInfo(ResponseConfig.tokenValidCode, ResponseConfig.tokenValidMsg);
//        }
//
//        try{
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json; charset=utf-8");
//            String json = new Gson().toJson(responseInfo);
//            PrintWriter out = response.getWriter();
//            out.append(json);
//        } catch (Exception e){
//            e.printStackTrace();
//        }
        String token = request.getHeader("x-access-token");
        if(token != null && token.length() > 0){
             return TokenUtil.verifyToken();
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
