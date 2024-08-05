package com.example.demo;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LikeData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	int postid;
	int userid;
	Date date;
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
	@Override
	public String toString() {
		return "LikeData [id=" + id + ", postid=" + postid + ", userid=" + userid + ", date=" + date + "]";
	}
	public LikeData(int id, int postid, int userid, Date date) {
		super();
		this.id = id;
		this.postid = postid;
		this.userid = userid;
		this.date = date;
	}
	public LikeData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
