package com.yash.ecom.userService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.userService.domain.UserSession;

public interface UserSessionRepository extends JpaRepository<UserSession, Long> {

	Optional<UserSession> findByUserAccountId(Long userAccountId);

}
