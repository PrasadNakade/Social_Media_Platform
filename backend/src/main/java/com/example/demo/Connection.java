package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Connection {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int cid;
	int userid1;
	int userid2;
	int connectionstatus;
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getUserid1() {
		return userid1;
	}
	public void setUserid1(int userid1) {
		this.userid1 = userid1;
	}
	public int getUserid2() {
		return userid2;
	}
	public void setUserid2(int userid2) {
		this.userid2 = userid2;
	}
	public int getConnectionstatus() {
		return connectionstatus;
	}
	public void setConnectionstatus(int connectionstatus) {
		this.connectionstatus = connectionstatus;
	}
	
}
