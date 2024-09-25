package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

@RestController
public class DummyControllerTest {

	@Autowired  //의존성 주입
	private UserRepository userRepository;
	
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		// DB에서 못 찾는 id 일 경우 user가 null이 됨
		//그러면 return null로 리턴이 되어 프로그램에 문제가 있을 수 있다.
		//그러면 Option로 너의 User 객체를 감싸서 가져올 테니 null인지 아닌지 판단해서 return해
		
		//User user = userRepository.findById(id).get();
		
		
		/* 
		  User user = userRepository.findById(id).orElseGet(new Supplier<User>() {
			@Override
			public User get() {
				return new User();				//id 이외의 값을 넣지만 그래도 null은 안나온다
			}
		});
		return user;
	}
		 */
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {			//new Supplier 부분은 lambda로 대체 가능 (ctrl + 1)

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당 유저는 없습니다!" +id);
			}
		});
		//요청 : 웹브라우저
		// user 객체 = 자바 오브젝트
		// 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
		// user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
		return user;
	}
		
	
	@GetMapping("/dummy/user/page")
	public List<User>pageList(@PageableDefault(size = 2,sort = "id",direction = Direction.DESC)Pageable pageable){
		Page <User> pagingusers =  userRepository.findAll(pageable);
		if (pagingusers.isFirst()) {
			//첫 번쨰로 올경우의 옵션
		}
		if (pagingusers.isLast()) {
			//마지막으로 출력되는 경우의 옵션
		}
		List<User> users  = pagingusers.getContent();
		return users;
	}
	
	//http://localhost:8081/blog/dummy/join(요청)
	//http의 body에 username,password,email 데이터를 가지고 요청
	@PostMapping("/dummy/join")
	public String join(@RequestBody User user) {
	//public String join(String username,String password, String email) {
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getEmail());
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료되었습니다";
	}
	//email,password 수정
	//save함수는 id를 전달하지 않으면 insert를 해주고
	//save함수는 id전달시 해당 id에 대한 데이터가 있으면 update
	//save함수는 id전달시 해당 id에 대한 데이터가 없으면 insert
	@Transactional			//함수 종료시 자동으로 commit 됨 
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) {
		System.out.println("id :" +id );
		System.out.println("password :" +requestUser.getPassword() );
		System.out.println("email :" +requestUser.getEmail() );
		
		User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("수정에 실패하였습니다"));
		//변경 감지 (Dirty Checking)
		//영속성 컨텍스트는 엔티티의 상태 변화를 감지하여 트랜잭션이 커밋되기 전에 변경된 내용을 데이터베이스에 자동으로 반영합니다. 이를 **변경 감지(Dirty Checking)**라고 합니다.
		user.setEmail(requestUser.getEmail());
		user.setPassword(requestUser.getPassword());
		//userRepository.save(user);		
		
		return user;
	}
	@DeleteMapping("/dummy/user/{id}")
	public String deleteUser(@PathVariable int id) {
	
		try {
		userRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			return "삭제에 실패하였습니다 해당 id는 DB에 없습니다";
		}
		
		//User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("삭제에 실패하였습니다"));
		//userRepository.delete(user);

		return "삭제되었습니다. id : "+id;
	}
}
