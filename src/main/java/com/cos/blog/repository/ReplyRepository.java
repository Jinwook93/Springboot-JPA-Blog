package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cos.blog.model.Board;
import com.cos.blog.model.Reply;
import com.cos.blog.model.User;
import java.util.List;
import java.util.Optional;


//DAO 역할
//자동으로 bean 등록가능
//@Repository		 // 생략가능
public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	@Modifying		//void, integer, int만 리턴이 가능
	@Query(value="INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1, ?2, ?3, now())", nativeQuery = true)	//nativeQuery: 내가 만든 쿼리 작동
	int mSave(int userId, int boardId, String content); // 업데이트된 행의 개수를 리턴해줌.  
}
