package com.yanghaoyi.user.controller;

import com.yanghaoyi.common.response.Response;
import com.yanghaoyi.common.response.ResponseHelper;
import com.yanghaoyi.token.exception.TokenException;
import com.yanghaoyi.token.util.TokenUtil;
import com.yanghaoyi.user.aop.auth.PermissionModule;
import com.yanghaoyi.user.aop.auth.enu.Module;
import com.yanghaoyi.user.model.UserEntity;
import com.yanghaoyi.user.pojo.result.LoginResult;
import com.yanghaoyi.user.pojo.result.VerifyCodeResult;
import com.yanghaoyi.user.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.yanghaoyi.user.service.constants.ErrCodeConstant.ERROR_PASSWORD;
import static com.yanghaoyi.user.service.constants.ErrCodeConstant.ERROR_TOKEN;
import static com.yanghaoyi.user.service.constants.ErrCodeConstant.ERROR_USER_ALREADY_REGISTER;

/**
 * @author : YangHaoYi on 2020/4/26.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/4/26.
 * Version : V 1.0
 */
@Log4j2
@Api(tags = "用户系统")
@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Resource
    private IUserService userService;

    @ApiOperation(value = "获取验证码",notes = "获取邮件验证码",httpMethod = "GET")
    @RequestMapping(value = "/verifyCode",method = RequestMethod.GET,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<VerifyCodeResult> verifyCode(@RequestParam(value="userName") String userName){
        return ResponseHelper.createSuccessResponse(userService.getVerifyCode(userName));
    }

    @ApiOperation(value ="验证码注册/登录",notes = "验证码注册/登录",httpMethod = "POST")
    @RequestMapping(value = "/verifyCode",method = RequestMethod.POST,produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> registerByVerifyCode(@RequestParam(value="userName") String userName, @RequestParam(value="verifyCode")String verifyCode){
        UserEntity userEntity;
        log.warn("查询开始");
        userEntity = userService.findUserByUserName(userName);
        log.warn("查询结束");
        if (userEntity != null) {
            //用户名已经注册,执行登录
            LoginResult loginResult = userService.loginByVerifyCode(userEntity,verifyCode);
            return ResponseHelper.createSuccessResponse(loginResult);
        }else {
            userEntity = userService.registerByVerifyCode(userName,verifyCode);
            return ResponseHelper.createSuccessResponse(userEntity);
        }
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<UserEntity> register(@RequestParam(value="userName") String userName, @RequestParam(value="password") String password){
        UserEntity userEntity;
        log.warn("查询开始");
        userEntity = userService.findUserByUserName(userName);
        log.warn("查询结束");
        if (userEntity != null) {
            //用户名已经注册
            return ResponseHelper.createResponse(ERROR_USER_ALREADY_REGISTER,"该用户名已经注册");
        }else {
            //用户名没有注册，注册用户
            log.warn("插入开始 ");
            userEntity = userService.insertUser(userName,password);
        }
        return ResponseHelper.createSuccessResponse(userEntity);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Object> login(@RequestParam(value="userName") String userName,@RequestParam(value="password") String password){
        String token = "";
        UserEntity userEntity;
        log.warn("查询开始");
        userEntity = userService.findUserByUserName(userName);
        log.warn("查询结束");
        if (userEntity != null) {
            if(userEntity.getPassword().equals(password)){
                //账号密码正确，登录成功
                log.warn("账号密码正确，登录成功 ");
                token = userService.createToken(userEntity);
                LoginResult loginResult = new LoginResult();
                loginResult.setToken(token);
                loginResult.setUserEntity(userEntity);
                return ResponseHelper.createSuccessResponse(loginResult);
            }else {
                //登录失败
                return ResponseHelper.createResponse(ERROR_PASSWORD,"帐号密码错误");
            }
        }else {
            //登录失败
            return ResponseHelper.createNotFoundResponse();
        }
    }

    @PermissionModule(belong= {Module.MASTER})
    @ApiOperation(value = "删除用户信息", notes = "删除用户信息", httpMethod = "DELETE")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<UserEntity> delete(@RequestParam(value="userId") int userId){
        userService.deleteUser(userId);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "更新用户信息", notes = "更新用户信息", httpMethod = "PUT")
    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<UserEntity> update(@RequestBody UserEntity userEntity){
        userService.updateUser(userEntity);
        return ResponseHelper.createSuccessResponse();
    }

    @ApiOperation(value = "查询用户信息", notes = "根据token查询用户信息", httpMethod = "GET")
    @RequestMapping(value = "/info", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<UserEntity> getUserInfo() {
        int userId = TokenUtil.getTokenUserId();
        String context = "hello " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
        return ResponseHelper.createSuccessResponse(userService.findUserById(userId));
    }

    @ApiOperation(value = "查询所有用户信息", notes = "查询所有用户信息", httpMethod = "GET")
    @RequestMapping(value = "/info/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<List<UserEntity>> getAllUserInfo() {
        return ResponseHelper.createSuccessResponse(userService.findAllUser());
    }

    @ApiOperation(value = "获取用户Id", notes = "根据token获取用户Id", httpMethod = "GET")
    @RequestMapping(value = "/userId", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Response<Integer> getUserId(){
        return ResponseHelper.createSuccessResponse(TokenUtil.getTokenUserId());
    }


}
