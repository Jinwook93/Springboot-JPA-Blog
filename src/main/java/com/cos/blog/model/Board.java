package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length=100)
	private String title;
	
	@Lob //대용량 데이터
	private String content; //섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨
	
	//@ColumnDefault("0")			안 쓸 예정. 직접 만듬
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) 	//Many: Board, User : One			//FetchType.EAGER: 무조건 즉시 들고 와라
	@JoinColumn(name="userid")
	private User user; //DB는 오브젝트를 저장할 수 없다. FK,자바는 오브젝트를 생성할 수 있다
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //삭제 시 연관되어 있는 하위 데이터들도 삭제
	//mappedBy 연관관계의 주인이 아니다. 난 FK가 아니다. DB에 컬럼을 만들지 마세요 
	//주인 : reply 테이블의 'board'를 의미								 // FetchType.LAZY: 선택적으로 들고 와라 
	//@JoinColumn(name="replyId") : DB 내의 값은 하나의 원자성, 1정규화만 가질 수 있다. 외래키를 만들 필요가 없다 
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")		//id순으로 내림차순
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
	
}
