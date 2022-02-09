package com.simon.october.cloud.upms.biz.controller;

import com.simon.oct.common.core.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * 获取当前用户全部信息
     * */
    @GetMapping("/info")
    public R info() {
        return R.ok();
    }
}
