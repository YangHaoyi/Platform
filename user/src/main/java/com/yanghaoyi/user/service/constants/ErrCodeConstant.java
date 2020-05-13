package com.yanghaoyi.user.service.constants;

/**
 * Created by 浩艺 on 2020/4/27.
 */
public class ErrCodeConstant {
    /**
     * <p>Discription:[token缺失]</p>
     */
    public static final int NO_TOKEN = 11001;
    /**
     * <p>Discription:[token失效]</p>
     */
    public static final int ERROR_TOKEN = 11002;

    /**
     * <p>帐号密码错误</p>
     */
    public static final int ERROR_PASSWORD = 11003;

    /**
     * <p>权限不足</p>
     */
    public static final int ERROR_AUTH = 11004;

    /**
     * <p>用户已经注册</p>
     */
    public static final int ERROR_USER_ALREADY_REGISTER = 12001;

    /**
     * <p>操作过快</p>
     */
    public static final int ERROR_VERFY_CODE_TOO_FAST = 12002;

    /**
     * <p>验证码错误</p>
     */
    public static final int ERROR_VERFY_CODE = 12003;


}
