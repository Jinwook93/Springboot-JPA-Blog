package com.cos.blog.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data 			//getter,setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {
private int id;			//변수에 직접 접근 못하도록 객체 지향적으로 접근하기 위해 private를 사용 
private  String name;			//즉 메서드로 변수값이 변경될 수 있도록 하는 것을 권장
private String email;
private  String password;



}
