package com.cos.blog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDTO;
import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.BoardService;
import com.cos.blog.service.UserService;
import com.mysql.cj.Session;

import jakarta.servlet.http.HttpSession;

@RestController
public class BoardApiController {

	@Autowired
	BoardService boardService;
	
	@PostMapping("/auth/board")
	public ResponseDTO<Integer> save(@RequestBody Board board,@AuthenticationPrincipal PrincipalDetail principalDetail) {
		boardService.boardWrite(board, principalDetail.getUser());
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);		//자바오브젝트를 JSON으로 변환해서 리턴 //성공일 경우 (200) 1을 리턴 , 실패시 -1
	}
	
	
	@DeleteMapping("/api/board/{id}")
	public ResponseDTO<Integer> deleteById(@PathVariable int id){
		boardService.boardDelete(id);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
		
	}
	@PutMapping("/api/board/{id}")
	public ResponseDTO<Integer> update(@PathVariable int id , @RequestBody Board board){
		boardService.updateBoard(id,board);
		return new ResponseDTO<Integer>(HttpStatus.OK.value(), 1);
		
	}
}
