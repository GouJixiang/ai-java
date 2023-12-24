package com.occn.ai.user.repository;

import com.occn.ai.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository(value = "userRepository")
public interface UserRepository extends JpaRepository<UserEntity, String> {

    Optional<UserEntity> findFirstByAccount(String account);

    UserEntity findByAccount(String account);

}
