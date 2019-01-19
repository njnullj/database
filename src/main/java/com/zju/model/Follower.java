package com.zju.model;


public class Follower {
	private int id;
	private int user_id;
	private String user_name;
	private int follower_user_id;
	private String follower_user_name;
	private String ts;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public int getFollower_user_id() {
		return follower_user_id;
	}
	public void setFollower_user_id(int follower_user_id) {
		this.follower_user_id = follower_user_id;
	}
	public String getFollower_user_name() {
		return follower_user_name;
	}
	public void setFollower_user_name(String follower_user_name) {
		this.follower_user_name = follower_user_name;
	}
	public String getTs() {
		return ts;
	}
	public void setTs(String ts) {
		this.ts = ts;
	}

}