package com.java.PlataformadeBlogs.dto;

import java.time.LocalDateTime;
import java.util.Set;


import com.java.PlataformadeBlogs.entities.Comment;

public class PostDTO {

	private Long id;
	
    private String title;
    private String content;
    private String author;
    private String hashtags;
    private LocalDateTime createdDate = LocalDateTime.now();
    private int inative = 0;
    
	private Set<Comment> comments;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getHashtags() {
		return hashtags;
	}

	public void setHashtags(String hashtags) {
		this.hashtags = hashtags;
	}
	public int getInative() {return inative;}
	public void setInative(int inative) {this.inative = inative;}
    public LocalDateTime getCreatedDate() {return createdDate;}
    public void setCreatedDate() {this.createdDate = LocalDateTime.now();}
    
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public PostDTO() {
		super();
	}	 
	
}
