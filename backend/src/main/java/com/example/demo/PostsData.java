package com.example.demo;

import java.util.Date;

//projection concept
public interface PostsData {
		
	int getid();
	String getContent();
	Date getDate();
	int getLikecount();
	String getUsername();
}
