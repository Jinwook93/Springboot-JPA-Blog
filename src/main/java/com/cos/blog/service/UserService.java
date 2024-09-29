package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
	
	private UserRepository userRepository;


	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional		//일을 처리하는 단위, 하나의 서비스가 하나의 트랜젝션을 가지기도 하지만 두개 이상의 복합적인 서비스를
	//하나의 트랜젝션으로 묶을 수도 있다.
	//성공이 되면 commit,실패가되면 rollback이 되는 코드 짜야함
	public void joinMember(User user) {			//수정 후		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	
	}
	
	
	
	/*   수정 전
	public int joinMember(User user) {				
		try {
		userRepository.save(user);
		return 1;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService: 회원가입(): " +e.getMessage());
		}
		return -1;
	}
	
	*/

// 스프링시큐리티 안쓰는 로그인 방식
//	@Transactional(readOnly = true)		//일을 처리하는 단위, 하나의 서비스가 하나의 트랜젝션을 가지기도 하지만 두개 이상의 복합적인 서비스를
//	//하나의 트랜젝션으로 묶을 수도 있다.
//	//성공이 되면 commit,실패가되면 rollback이 되는 코드 짜야함
//	public User loginMember(User user) {			//수정 후		
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	
	
}
