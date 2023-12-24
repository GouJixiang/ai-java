package com.occn.ai.user.service.impl;

import com.occn.ai.common.enums.ReponseCodeEnum;
import com.occn.ai.exception.BusinessException;
import com.occn.ai.user.domain.UserEntity;
import com.occn.ai.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByAccount(account);
        if (user == null) {
            throw new BusinessException(ReponseCodeEnum.USER_NOTFOUND, "用户" + account + "不存在！");
        }
        return new User(
                user.getAccount(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
