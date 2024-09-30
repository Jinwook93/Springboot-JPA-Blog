package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import java.util.List;
import java.util.Optional;


//DAO 역할
//자동으로 bean 등록가능
//@Repository		 // 생략가능
public interface BoardRepository extends JpaRepository<Board, Integer>{
	Optional<Board> findById(int id);
	Optional<Board> deleteById(int id);
}
