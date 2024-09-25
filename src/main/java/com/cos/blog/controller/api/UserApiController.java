package com.cos.blog.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.User;

@RestController
public class UserApiController {

	@PostMapping("/api/user")
	public ResponseDTO<Integer> save(@RequestBody User user) {
		System.out.println("UserApiController : post api 호출됨");
		//실제 db에 INSERT를 하고 아래에서 RETURN이 되면 된다.
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);		//성공일 경우 (200) 1을 리턴
	}
}
