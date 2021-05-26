package com.yash.ecom.userService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yash.ecom.userService.domain.UserAccount;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
	
	Optional<UserAccount> findByEmailAndPassword(String email, String password);
	
	Optional<UserAccount> findByEmail(String email);

}
