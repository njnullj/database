package com.zju.model;

public class Post {
	private long id;
	private long post_author;
	private String date;
	private int like_count;
	private int comment_count;
	private String post_content;
	private String post_url;
	private String title;
	private int forward;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPost_author() {
		return post_author;
	}
	public void setPost_author(long post_author) {
		this.post_author = post_author;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getPost_content() {
		return post_content;
	}
	public void setPost_content(String post_content) {
		this.post_content = post_content;
	}
	public String getPost_url() {
		return post_url;
	}
	public void setPost_url(String post_url) {
		this.post_url = post_url;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getForward() {
		return forward;
	}
	public void setForward(int forward) {
		this.forward = forward;
	}
	
	
	
}
