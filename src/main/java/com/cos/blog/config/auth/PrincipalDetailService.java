package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

//스프 링 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를 
//스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다
@Service
public class PrincipalDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	
	//스프링이 로그인 요청을 가로챌 때 username,password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 함
	//username이 DB에 있는지만 확인해주면 됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User principal = userRepository.findByUsername(username)
				.orElseThrow(()->{				// User 타입일 떄 정상으로 받아주고 그 외에는 Else를 던진다
						return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다 " +username);
				});
		return new PrincipalDetail(principal);			//시큐리티 세션 정보에 유저 정보가 저장이 된다
	}

	

}
