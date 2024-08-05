package com.example.demo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Comments {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int postid;
	int userid;
	Date date;
	String username;
	String comment;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comments [id=" + id + ", postid=" + postid + ", userid=" + userid + ", date=" + date + ", username="
				+ username + ", comment=" + comment + "]";
	}
	public Comments(int id, int postid, int userid, Date date, String username, String comment) {
		super();
		this.id = id;
		this.postid = postid;
		this.userid = userid;
		this.date = date;
		this.username = username;
		this.comment = comment;
	}
	public Comments() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
