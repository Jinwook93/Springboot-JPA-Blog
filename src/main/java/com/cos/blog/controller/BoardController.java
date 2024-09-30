package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@GetMapping({"", "/"})		
	public String index(Model model) {
		// /WEB-INF/views/index.jsp
		model.addAttribute("boards", boardService.boardList());
		return "index";		//viewResolver 작동!
	}
	
	@GetMapping("/board/saveForm")		
	public String saveForm() {
		// /WEB-INF/views/board/saveForm
		return "board/saveForm";
	}
//	@GetMapping({"", "/"})		
//	public String index(@AuthenticationPrincipal PrincipalDetail principalDetail) {
//		// /WEB-INF/views/index.jsp
//		System.out.println("로그인 된 사용자 : " +principalDetail.getUsername());		//컨트롤러에서 세션을 어떻게 찾는지?
//		return "index";
//	}

}
