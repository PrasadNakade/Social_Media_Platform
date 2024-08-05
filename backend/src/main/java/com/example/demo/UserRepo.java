package com.example.demo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{
	
	int countByMobile(long number);
	
	int countByEmail(String email);

	int countByUsername(String name);

	int countByPassword(String password);
	
	User findByMobile(long mobile);

	User findByUsername(String username);

	List<User> findTop20ByUsernameStartsWith(String name);

	List<User> findByIdIn(List<Integer> ids);

	

	//Optional<User> findById(User userid);
	
	 

}
