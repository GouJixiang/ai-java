package com.occn.ai.user.service;

import com.occn.ai.user.dao.User;
import com.occn.ai.user.domain.UserEntity;
import com.occn.ai.user.vo.UserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity userToUserEntity(User user);

    UserVO userToUserVO(User user);

    UserVO userEntityToUserVO(UserEntity user);
}
