package com.java.PlataformadeBlogs.entities;

import java.time.LocalDateTime;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String commenter;
	private String content;
	private LocalDateTime commentedDate = LocalDateTime.now();
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Post_id", nullable = false)
	private Post post;

	public Comment() {
		super();
	}
	public Comment(Long id) {  this.id = id; }
	public long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCommenter() {
		return commenter;
	}

	public void setCommenter(String commenter) {
		this.commenter = commenter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
    public LocalDateTime getCommentedDate() {return commentedDate;}
    public void CommentedDate(LocalDateTime commentedDate) {this.commentedDate = commentedDate;}
    
	public Post getPost() {
		return post;
	}

	public void setPost(Post publication) {
		this.post = publication;
	}



}
