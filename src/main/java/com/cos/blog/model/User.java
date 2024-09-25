package com.cos.blog.model;

import java.sql.Timestamp;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//ORM -> Java(다른 언어) Object -> 테이블로 매핑해주는 기술
@Entity	//User 클래스가 MySQL에 테이블이 생성됨
@Data
@NoArgsConstructor
@AllArgsConstructor
//@DynamicInsert			//insert 시 null인 부분은 제외시켜준다	//Dynamicinsert를 안 쓰고 다른 방식으로 기능을 처리할 것임
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다
	private int id ; //시퀀스, auto_increment
	@Column(nullable=false, length=30)
	private String username; //아이디
	@Column(nullable=false, length=100)	//해쉬된 비밀번호 떄문에 넉넉히 100으로 줬다.
	private String password;
	@Column(nullable=false, length=30)
	private String email;
	
	@ColumnDefault("'user'")
	//private String role; //Enum을 쓰는 것이 좋다	
	@Enumerated(EnumType.STRING)
	private RoleType role;		//ADMIN,USER
	
	@CreationTimestamp //시간이 자동 입력
	private Timestamp createDate;
}
