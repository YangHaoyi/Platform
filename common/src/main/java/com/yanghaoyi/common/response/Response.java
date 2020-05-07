package com.yanghaoyi.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by 浩艺 on 2020/4/27.
 */
@ApiModel
public class Response<T> {
    @ApiModelProperty(
            position = 1,
            required = true,
            value = "返回内部编码:0:成功 / 非0:失败"
    )
    private int code;
    @ApiModelProperty(
            position = 2,
            required = true,
            value = "消息描述"
    )
    private String description = "";
    @ApiModelProperty(
            position = 3,
            required = true,
            value = "最后更新时间"
    )
    private long lastUpdateTime;
    @ApiModelProperty(
            position = 4,
            required = true,
            value = "返回体内容"
    )
    private T payload;

    public Response() {
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public T getPayload() {
        return this.payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String toString() {
        return "Response{code=" + this.code + ", description='" + this.description + '\'' + ", lastUpdateTime=" + this.lastUpdateTime + ", payload=" + this.payload + '}';
    }
}
