package com.occn.ai.user.service.impl;

import com.occn.ai.common.enums.ReponseCodeEnum;
import com.occn.ai.common.utils.RsaUtils;
import com.occn.ai.exception.BusinessException;
import com.occn.ai.security.JwtService;
import com.occn.ai.user.dao.User;
import com.occn.ai.user.domain.UserEntity;
import com.occn.ai.user.repository.UserRepository;
import com.occn.ai.user.service.UserMapper;
import com.occn.ai.user.service.UserService;
import com.occn.ai.user.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private AuthenticationProvider authenticationProvider;

    @Resource
    private JwtService jwtService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * 用户登录
     *
     * @param account  用户账号
     * @param password 用户密码
     * @return 用户登录信息
     */
    @Override
    public Map<String, Object> login(String account, String password) {
        Authentication authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(account, RsaUtils.decryptRSA(password)));
        if (authentication == null) {
            throw new BusinessException("用户校验失败");
        }
        String token = jwtService.generateToken(account);
        String refreshToken = jwtService.generateRefreshToken(account);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new HashMap<String, Object>() {
            {
                put("token", token);
                put("refreshToken", refreshToken);
            }
        };
    }

    /**
     * 用户注册
     *
     * @param user 注册用户
     * @return 注册成功返回的用户视图对象
     */
    @Transactional
    @Override
    public UserVO register(User user) {
        if (userRepository.findByAccount(user.getAccount()) != null) {
            throw new BusinessException(ReponseCodeEnum.USER_EXISTS);
        }
        UserEntity userEntity = UserMapper.INSTANCE.userToUserEntity(user);
        userEntity.setPassword(bCryptPasswordEncoder.encode(userEntity.getPassword()));
        userEntity = userRepository.saveAndFlush(userEntity);
        return UserMapper.INSTANCE.userEntityToUserVO(userEntity);
    }
}
