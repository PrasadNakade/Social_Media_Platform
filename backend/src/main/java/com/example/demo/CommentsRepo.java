package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments, Integer> 
{
	
	List<Comments> findByPostidOrderByIdDesc(int postid);
		
}