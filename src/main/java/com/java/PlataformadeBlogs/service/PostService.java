package com.java.PlataformadeBlogs.service;

import com.java.PlataformadeBlogs.dto.PostDTO;
import com.java.PlataformadeBlogs.dto.PostResponse;


public interface PostService {
	
	public PostResponse getAllPost(int numPage, int sizePage);
	
	public PostDTO createPost(PostDTO publicationDTO);
	
	public PostDTO findPostById(Long id);
	
	public PostDTO updatePost(PostDTO publicationDTO, Long id);
	
	public void deletePost(Long id);

	public PostDTO updatePostPartial(Long id, PostDTO partialPostDTO);
}
