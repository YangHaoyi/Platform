package com.yanghaoyi.user.pojo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author : YangHaoYi on 2020/5/9.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/9.
 * Version : V 1.0
 */
@Data
@ApiModel
public class VerifyCodeResult implements Serializable {
    @ApiModelProperty(notes = "用户名")
    private String userName;
    @ApiModelProperty(notes = "验证码")
    private String verifyCode;
}
