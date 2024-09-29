package com.cos.blog.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;

@Controller
public class BoardController {

	
//	@GetMapping({"", "/"})		
//	public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
//		// /WEB-INF/views/index.jsp
//		System.out.println("로그인 된 사용자 : " +principalDetail.getUsername());		//컨트롤러에서 세션을 어떻게 찾는지?
//		return "index";
//	}
	@GetMapping({"", "/"})		
	public String index() {
		// /WEB-INF/views/index.jsp
		return "index";
	}
}
