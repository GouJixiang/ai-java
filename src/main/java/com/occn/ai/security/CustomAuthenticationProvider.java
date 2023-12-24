package com.occn.ai.security;

import com.occn.ai.common.enums.ReponseCodeEnum;
import com.occn.ai.exception.BusinessException;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = userDetailsService.loadUserByUsername(account);
        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new BusinessException(ReponseCodeEnum.PASSWORD_FAIL, "用户密码错误！");
        }
        CustomAuthenticationToken result = new CustomAuthenticationToken(userDetails, null, Collections.emptyList());
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationProvider.class.isAssignableFrom(authentication);
    }
}
