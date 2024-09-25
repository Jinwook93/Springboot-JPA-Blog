package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

//사용자가 요청 -> 응답(HTML 파일)
//@Controller

//사용자가 요청 -> 응답(Data 파일)
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest : ";
			
	@GetMapping("/http/lombok")		
	private String lombokTest() {
		//Member m = new Member(1,"ssar","1234","email");
		Member m = Member.builder().email("email_builer").name("ssar_builder")
				.password("12341234").build();
		System.out.println(TAG+"getter:"+m.getId()+" "+m.getEmail()+" "+m.getPassword());		
		m.setId(5000);
		System.out.println(TAG+"setter:"+m.getId());	
		return "lombok test 완료";
	}
	
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		System.out.println(TAG+"setter");
		return "get 요청";
	}
	@PostMapping("/http/post")
	public String postTest() {
		return "post 요청";
	}
	@PutMapping("/http/put")
	public String putTest() {
		return "put 요청";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
}
