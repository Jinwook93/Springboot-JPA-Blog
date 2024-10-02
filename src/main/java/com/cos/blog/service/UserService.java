package com.cos.blog.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;


//스프링이 컴포넌트 스캔을 통해 Bean에 등록을 해줌. IoC를 해준다.
@Service
public class UserService {
	
	private UserRepository userRepository;


	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Transactional
	public void updateMember(User user) {
		//수정시에는 영속성 컨텍스트 User 오브젝트를 영속화 시키고, 영속화된 User 오브젝트를 수정
		//select를 해서 User 오브젝트를 DB로부터 가져오는 이유는 영속화를 하기 위해서 !!!!
		// 영속화된 오브젝트를 변경하면 자동으로 DB에 update문을 날려준다
		User updatedUser = userRepository.findById(user.getId()).orElseThrow(()-> new IllegalArgumentException("수정할 회원정보가 존재하지 않습니다"));
	
		// Validate 체크 => oauth 필드에 값이 없으면 수정 가능
		if(updatedUser.getOauth() == null || updatedUser.getOauth().equals("")) {
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		updatedUser.setPassword(encPassword);
		updatedUser.setEmail(user.getEmail());
		}
	    // 변경된 사용자 정보를 세션에 반영
	    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

	    // 현재 SecurityContext를 가져와 Authentication을 업데이트
	    SecurityContext securityContext = SecurityContextHolder.getContext();
	    securityContext.setAuthentication(authentication);

			//회원 수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다
		// 영속화된 persistence 객체의 변화가 감지되면 더티체킹이되어 update문을 날려준다
	}
	
	@Transactional		//일을 처리하는 단위, 하나의 서비스가 하나의 트랜젝션을 가지기도 하지만 두개 이상의 복합적인 서비스를
	//하나의 트랜젝션으로 묶을 수도 있다.
	//성공이 되면 commit,실패가되면 rollback이 되는 코드 짜야함
	public void joinMember(User user) {			//수정 후		
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
	//	userRepository.save(user);

	
	}
	
	
	@Transactional(readOnly = true)		
	public User findMember(String username) {				
		User user =userRepository.findByUsername(username).orElseGet(() -> new User());
		return user;
	}
	
//		//오버로딩 (시험 삼아 만들어 봄)
//	@Transactional
//	public void joinMember(KakaoProfile kakaoProfile) {
//	    // 카카오 이메일 정보 추출
//	    String email = kakaoProfile.getKakao_account().getEmail();
//
//	    // email을 사용해 기존 회원 여부 확인
//	    User existingUser = userRepository.findByEmail(email);
//
//	    if (existingUser == null) {
//	        // 회원이 존재하지 않으면 자동 회원가입 진행
//	        System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
//
//	        // 임시 패스워드 생성
//	        UUID garbagePassword = UUID.randomUUID();
//	        User user = User.builder()
//	                .username(kakaoProfile.getProperties().getNickname())
//	                .email(email)
//	                .password(garbagePassword.toString())
//	                .build();
//
//	        // 패스워드 암호화
//	        String rawPassword = user.getPassword();
//	        String encPassword = encoder.encode(rawPassword);
//	        user.setPassword(encPassword);
//
//	        // 권한 설정
//	        user.setRole(RoleType.USER);
//
//	        // 회원 정보 저장
//	        userRepository.save(user);
//	    } else {
//	        // 회원이 이미 존재하는 경우
//	        System.out.println("해당 이메일로 이미 가입된 회원이 있습니다: " + existingUser.getUsername());
//	    }
//	}

	
	
	
	/*   수정 전
	public int joinMember(User user) {				
		try {
		userRepository.save(user);
		return 1;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("UserService: 회원가입(): " +e.getMessage());
		}
		return -1;
	}
	
	*/

// 스프링시큐리티 안쓰는 로그인 방식
//	@Transactional(readOnly = true)		//일을 처리하는 단위, 하나의 서비스가 하나의 트랜젝션을 가지기도 하지만 두개 이상의 복합적인 서비스를
//	//하나의 트랜젝션으로 묶을 수도 있다.
//	//성공이 되면 commit,실패가되면 rollback이 되는 코드 짜야함
//	public User loginMember(User user) {			//수정 후		
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	
	
}
