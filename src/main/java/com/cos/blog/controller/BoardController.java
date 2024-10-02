package com.cos.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	//게시글 정보 보기
	@GetMapping("/board/{boardid}")		
	public String detailForm(Model model, @PathVariable int boardid) {

		model.addAttribute("board", boardService.boardDetail(boardid));
		return "board/detail";
	}
	
	
	
	@GetMapping({"", "/"})		
	public String index(Model model, @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
		
Page<Board> boardList = boardService.boardList(pageable);
		
		int nowPage = boardList.getPageable().getPageNumber() + 1; // 현재페이지 : 0 에서 시작하기에 1을 더해준다.		//pageNumber: 현재 위치하고 있는 페이지
		int firstlistpage = 1;
		int lastlistpage = 3;
		boolean listpagecheckflg = false;
		
		// 페이지 번호 리스트틀 3개씩 출력하도록 한다.
		// 마지막 리스트가 3개 미만일 경우는 남은 번호만 출력하도록 한다.
		while (listpagecheckflg == false) {
			if (boardList.getTotalPages() == 0) {			//TotalPages : 전체 페이지 수 (1부터 시작)
				lastlistpage = 1;
				listpagecheckflg = true;
			}
			if (lastlistpage > boardList.getTotalPages()) {
				lastlistpage = boardList.getTotalPages();
			}
			if (nowPage >= firstlistpage && nowPage <= lastlistpage) {
				listpagecheckflg = true;
			} else {
				firstlistpage += 3;
				lastlistpage += 3;
			}
		}
		
		// 현재 페이지 번호
		model.addAttribute("nowlistpageno", nowPage);
		// 총 페이지
		model.addAttribute("totalpagesize", boardList.getTotalPages());
		// 페이지 번호 리스트 (첫)
		model.addAttribute("firstlistpage", firstlistpage);
		// 페이지 번호 리스트 (마지막)
		model.addAttribute("lastlistpage", lastlistpage);
		// 페이지, 게시글 정보
		model.addAttribute("boards", boardList);
		
		// /WEB-INF/views/index.jsp
		return "index";
	}

	@GetMapping("/board/{boardid}/updateForm")
	public String updateForm(@PathVariable int boardid, Model model) {
		model.addAttribute("board", boardService.boardDetail(boardid));
		return "board/updateForm";
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
