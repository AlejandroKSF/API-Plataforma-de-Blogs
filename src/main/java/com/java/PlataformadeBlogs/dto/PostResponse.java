package com.java.PlataformadeBlogs.dto;

import java.util.List;

public class PostResponse {

	private List<PostDTO> contentList;

	public List<PostDTO> getContentList() {
		return contentList;
	}

	public void setContentList(List<PostDTO> contentList) {
		this.contentList = contentList;
	}

	public PostResponse() {
		super();
	}	 
	
}
