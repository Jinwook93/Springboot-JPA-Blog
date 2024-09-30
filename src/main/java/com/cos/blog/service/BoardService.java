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
import org.springframework.web.bind.annotation.RequestBody;

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

	@Transactional
	public void updateBoard(int boardid, Board requestboard) {
	    // 영속화된 board 객체를 찾음
	    Board board = boardRepository.findById(requestboard.getId()).orElseThrow(
	            () -> new IllegalArgumentException("수정 실패: 아이디를 찾을 수 없습니다"));

	    // 영속화된 board 객체의 필드 값을 직접 수정
	    board.setTitle(requestboard.getTitle());
	    board.setContent(requestboard.getContent());

//  TroubleShooting	    
//	    빌더 패턴은 객체 생성 시 유용하지만, 영속화된 객체의 수정에는 적합하지 않습니다. 
//	    더티 체킹을 이용하려면 영속화된 객체의 필드를 직접 수정해야 합니다. 
//	    JPA의 더티 체킹은 영속성 컨텍스트에 등록된 객체의 필드 값이 변경된 것을 감지하고, 
//	    트랜잭션이 종료될 때 DB에 반영하는 방식이기 때문에 영속화된 객체에서 직접 수정이 이루어져야 합니다.
//	    board.builder().title(requestboard.getTitle()).content(requestboard.getContent()).build(); 는 사용할 수 없다
	        
		//해당 함수로 종료시 트랜젝션이 종료된다. 이 떄 더티체킹-자동업데이트 됨 db-flush
	}
	

	
	


}
