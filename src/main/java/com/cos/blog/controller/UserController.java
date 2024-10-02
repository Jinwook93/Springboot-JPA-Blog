package com.cos.blog.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.KakaoProfile;
import com.cos.blog.model.OAuthToken;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	UserService userService;
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	//인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
	// 그냥 주소가 / 이면 index.jsp 허용
	// static 이하에 있는 /js/**, /css/**,/image/** 
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	
	@GetMapping("/auth/kakao/callback")
//	public @ResponseBody String kakaoCallback(String code) {	//Data 를 리턴
	public String kakaoCallback(String code) {	//Data 를 리턴
		//POST 방식으로 key-value 데이터를 요청 (카카오쪽으로)
		//Retrofit2
		//OkHttp
		//RestTemplate
		
		RestTemplate rt = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		
		//HttpHeader 오브젝트 생성
		headers.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		
		//HttpBody 오브젝트 생성
		params.add("grant_type", "authorization_code");
		params.add("client_id", "09ee75cc50e5c24cc8624691de8fe75a");
		params.add("redirect_uri", "http://localhost:8081/auth/kakao/callback");
		params.add("code", code);
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoTokenRequest = new HttpEntity<>(params,headers);
		
		//http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,			//httpEntity
				String.class
				);
		
		//JSON Data를 Object에 담기
		//Gson, Json Simple, ObjectMapper 등이 쓰임 (우리는 ObjectMapper를 사용할 것임)
		ObjectMapper objectMapper = new ObjectMapper();
		OAuthToken oAuthToken=null;
		
		try {
			oAuthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
//		return "카카오 인증 완료: 토큰 요청에 대한 코드값: "+ response;
//		return response.getBody();
		System.out.println("카카오 액세스 토큰 " + oAuthToken.getAccess_token());
		
		
		//로그인 사용자 정보 조회
		
		//POST 방식으로 key-value 데이터를 요청 (카카오쪽으로)
		//Retrofit2
		//OkHttp
		//RestTemplate
		
		RestTemplate rt2 = new RestTemplate();
		
		//HttpHeader 오브젝트 생성
		HttpHeaders headers2 = new HttpHeaders();	
		headers2.add("Authorization", "Bearer "+oAuthToken.getAccess_token());
		headers2.add("Content-type","application/x-www-form-urlencoded;charset=utf-8");
		
		//HttpHeader와 HttpBody를 하나의 오브젝트에 담기
		HttpEntity<MultiValueMap<String,String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);
		
		//http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답을 받음
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest2,			//httpEntity
				String.class
				);
		//JSON Data를 Object에 담기
		//Gson, Json Simple, ObjectMapper 등이 쓰임 (우리는 ObjectMapper를 사용할 것임)
		ObjectMapper objectMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile  = null;
		
		try {
			kakaoProfile = objectMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	
		

		System.out.println(response2.getBody());
		System.out.println("카카오 아이디 (번호): "+kakaoProfile.getId());
		System.out.println("카카오 이메일 : "+kakaoProfile.getKakao_account().getEmail());
		System.out.println("카카오 사용자 : "+kakaoProfile.getProperties().getNickname());
	
		// UUID란 -> 중복되지 않는 어떤 특정 값을 만들어내는 알고리즘		//기존 회원의 경우 UUID의 중복되지 않는 값 떄문에 로그인 시 문제가 일어날 수 있다 따라서 사용하지 않을 예정
		//UUID garbagePassword = UUID.randomUUID();
		//System.out.println("블로그 서버 패스워드 : "+garbagePassword);
		
		System.out.println("블로그 서버 패스워드 : "+cosKey);
		
		//userService.joinMember(kakaoProfile);			//joinMember 오버로딩 테스트
		
		User kakaoUser = User.builder()
				.username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
				.password(cosKey)
				.email(kakaoProfile.getKakao_account().getEmail())
				.oauth("kakao")
				.build();
		
		// 가입자 혹은 비가입자 체크 해서 처리
		User originUser = userService.findMember(kakaoUser.getUsername());

		if(originUser.getUsername() == null) {
			System.out.println("기존 회원이 아니기에 자동 회원가입을 진행합니다");
			userService.joinMember(kakaoUser);
		}
		System.out.println("자동 로그인을 진행합니다");
		
//		//세션 저장 과정 진행
		PrincipalDetail principalDetail = new PrincipalDetail(kakaoUser);					//서비스에 가입된 User를 해야한다. (originUser는 자격이 없다고 뜸)
	Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(),cosKey)); //서비스에 가입된 User를 해야한다. (originUser는 자격이 없다고 뜸)

//		PrincipalDetail principalDetail = new PrincipalDetail(kakaoUser);
//		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(principalDetail, principalDetail.getPassword(), principalDetail.getAuthorities()));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
		
		
//		return response2.getBody();
		return "redirect:/";
	}
	
	
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
/*	수정 전
	@GetMapping("/user/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/user/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	
	
	*/
	}
