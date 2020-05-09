package com.yanghaoyi.user.pojo.result;

import com.yanghaoyi.user.model.UserEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author : YangHaoYi on 2020/5/9.
 * Email  :  yang.haoyi@qq.com
 * Description :
 * Change : YangHaoYi on 2020/5/9.
 * Version : V 1.0
 */
@Data
@ApiModel
public class LoginResult {
    @ApiModelProperty(notes = "token令牌")
    private String token;
    @ApiModelProperty(notes = "用户信息")
    private UserEntity userEntity;
}
