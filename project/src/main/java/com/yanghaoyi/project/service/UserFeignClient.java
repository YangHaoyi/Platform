package com.yanghaoyi.project.service;


import com.yanghaoyi.common.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("USER")
@Component
public interface UserFeignClient {
    @RequestMapping("api/v1/user/userId")
    Response<Integer> getUserId();
}
