package com.cos.blog.service;

import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.Board;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.BoardRepository;
import com.cos.blog.repository.UserRepository;


//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class BoardService {
	
	@Autowired
	private BoardRepository boardRepository;

	@Transactional		//일을 처리하는 단위, 하나의 서비스가 하나의 트랜젝션을 가지기도 하지만 두개 이상의 복합적인 서비스를
	//하나의 트랜젝션으로 묶을 수도 있다.
	//성공이 되면 commit,실패가되면 rollback이 되는 코드 짜야함
	public void boardWrite(Board board, User user) {			//title, content
			board.setCount(0);
			board.setUser(user);
			boardRepository.save(board);
	}

@Transactional(readOnly = true)
	public Page<Board> boardList(Pageable pageable) {
	
		return boardRepository.findAll(pageable);
	}

@Transactional(readOnly = true)
	public Board boardDetail(int boardid) {
	return 	boardRepository.findById(boardid).orElseThrow(
				new Supplier<IllegalArgumentException>() {

					@Override
					public IllegalArgumentException get() {
						return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다");
					}
					
				});
		
	}
	
	@Transactional
	public void boardDelete(int boardid) {
	    // 먼저 게시글이 존재하는지 확인
	    Board board = boardRepository.findById(boardid)
	        .orElseThrow(() -> new IllegalArgumentException("삭제 실패 : 아이디를 찾을 수 없습니다"));
	  //orElseThrow는 findById()와 같이 Optional 객체를 반환하는 메서드에서 사용할 수 있으며, deleteById()와 같이 반환값이 없는 메서드에서는 사용할 수 없습니다
	    
	    // 존재한다면 삭제 수행
	    boardRepository.delete(board);
	}


}
