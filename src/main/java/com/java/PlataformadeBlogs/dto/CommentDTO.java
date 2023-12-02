package com.java.PlataformadeBlogs.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CommentDTO {
	@JsonIgnore
	private Long id;
    private String commenter;
    private String content;
	public CommentDTO() {
		super();
	}
	
	public Long getId() {
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
	

}
