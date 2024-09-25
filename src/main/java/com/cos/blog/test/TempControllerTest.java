package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	@GetMapping("show/image")
	public String showImage() {
		return "/a.jpg";
	}
	@GetMapping("show/html")
	public String showHtml() {
		return "/test.html";
	}
	
//	@GetMapping("show/jsp")	수정 전
//	public String showJsp() {
//		return "/testjsp.jsp";
//	}
	
	@GetMapping("show/jsp")		//수정 후
	public String showJsp() {
		//prefix : /WEB-INF/views
		//suffix : .jsp
		//풀네임 : /WEB-INF/views/testjsp.jsp
		return "testjsp";
	}
}
