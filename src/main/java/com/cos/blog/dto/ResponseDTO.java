package com.cos.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO<T> {
	int status;			//@AllArgsConstructor의 경우 입력된 순대로 인자를 받는다. 즉, status,data 순으로 받는다 
	T data;	

}
