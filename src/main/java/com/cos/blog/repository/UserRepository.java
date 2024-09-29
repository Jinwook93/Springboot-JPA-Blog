package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.User;
import java.util.List;
import java.util.Optional;


//DAO 역할
//자동으로 bean 등록가능
//@Repository		 // 생략가능
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//SELECT * FROM user WHERE username =1?;
	Optional<User> findByUsername(String username);
	
	//=========스프링 시큐리티 사용 전================
	
	
	//JPA Naming쿼리
	// Select * from user where username =?1 and password =?2;
//	User findByUsernameAndPassword(String username, String password);

	//밑의 방법으로도 로그인 기능 구현 가능
	//@Query(value= "Select * from user where username =?1 and password =?2")
	//User login(String username, String password);
}
