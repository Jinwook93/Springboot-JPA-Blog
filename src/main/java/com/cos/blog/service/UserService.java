package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
	
	private UserRepository userRepository;


	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional		//일을 처리하는 단위, 하나의 서비스가 하나의 트랜젝션을 가지기도 하지만 두개 이상의 복합적인 서비스를
	//하나의 트랜젝션으로 묶을 수도 있다.
	//성공이 되면 commit,실패가되면 rollback이 되는 코드 짜야함
	public void joinMember(User user) {			//수정 후		
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
}
