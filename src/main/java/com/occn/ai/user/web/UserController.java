package com.occn.ai.user.web;

import com.occn.ai.common.bean.RestResult;
import com.occn.ai.user.dao.User;
import com.occn.ai.user.service.UserService;
import com.occn.ai.user.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @ResponseBody
    @PostMapping("/login")
    public RestResult<?> login(@RequestParam("account") String account, @RequestParam("password") String password) {
        Map<String, Object> userInfo = userService.login(account, password);
        if (userInfo == null) {
            return RestResult.fail("登录失败！");
        }
        return RestResult.ok(userInfo);
    }

    @GetMapping("/test")
    public String test() {
        return "你真棒👍🏻";
    }

    @ResponseBody
    @PostMapping("/register")
    public RestResult<UserVO> register(@RequestBody User user) {
        return RestResult.ok(userService.register(user));
    }

}
