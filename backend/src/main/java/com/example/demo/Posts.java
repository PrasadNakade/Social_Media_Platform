package com.example.demo;

import java.util.Date;

import javax.xml.crypto.Data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	Date date;
	int userid;
	int likecount;
	String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getLikecount() {
		return likecount;
	}
	public void setLikecount(int likecount) {
		this.likecount = likecount;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "Posts [id=" + id + ", date=" + date + ", userid=" + userid + ", likecount=" + likecount + ", content="
				+ content + "]";
	}
	public Posts(int id, Date date, int userid, int likecount, String content) {
		super();
		this.id = id;
		this.date = date;
		this.userid = userid;
		this.likecount = likecount;
		this.content = content;
	}
	public Posts() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
