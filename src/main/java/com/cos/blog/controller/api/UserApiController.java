package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.mysql.cj.Session;

import jakarta.servlet.http.HttpSession;

@RestController
public class UserApiController {

	private UserService userService;

	@Autowired
	public UserApiController(UserService userService) {
		this.userService = userService;
	}

	//@Autowired						//session을 자동으로 bean 매핑을 시켜서 만들 수 있음
	//private HttpSession session;


	
	@PostMapping("/auth/joinProc")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : post api 호출됨");
		//user.setRole(RoleType.USER);
		userService.joinMember(user);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);		//자바오브젝트를 JSON으로 변환해서 리턴 //성공일 경우 (200) 1을 리턴 , 실패시 -1
	}
	
	
		
	
// 스프링 시큐리티 사용하지 않는 회원가입 방식
//	@PostMapping("/api/user")
//	public ResponseDTO<Integer> save(@RequestBody User user) {
//		System.out.println("UserApiController : post api 호출됨");
//		//실제 db에 INSERT를 하고 아래에서 RETURN이 되면 된다.
//		user.setRole(RoleType.USER);
//		userService.joinMember(user);
//		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);		//자바오브젝트를 JSON으로 변환해서 리턴 //성공일 경우 (200) 1을 리턴 , 실패시 -1
//	}
	
	//전통적인 로그인 방식이며 이 방식을 사용하지않고 스프링 시큐리티를 사용할 것이다
//	@PostMapping("/api/user/login")
//	public ResponseDTO<Integer> login(@RequestBody User user,HttpSession session) {
//		System.out.println("UserApiController : 로그인 호출됨");
//		User principal = userService.loginMember(user);
//		
//		if(principal != null) {
//			session.setAttribute("principal",principal);
//		}
//		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);		//자바오브젝트를 JSON으로 변환해서 리턴 //성공일 경우 (200) 1을 리턴 , 실패시 -1
//	}
	
	
	
}
