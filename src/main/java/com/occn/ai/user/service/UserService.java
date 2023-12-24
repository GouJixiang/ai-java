package com.occn.ai.user.service;

import com.occn.ai.user.dao.User;
import com.occn.ai.user.vo.UserVO;

import java.util.Map;

public interface UserService {

    /**
     * 用户登录
     * @param account  用户账号
     * @param password 用户密码
     * @return 用户登录信息
     */
    Map<String, Object> login(String account, String password);

    /**
     * 用户注册
     * @param user 注册用户
     * @return 注册成功返回的用户视图对象
     */
    UserVO register(User user);
}
