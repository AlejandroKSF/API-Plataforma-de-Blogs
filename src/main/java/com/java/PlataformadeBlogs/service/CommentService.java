package com.java.PlataformadeBlogs.service;

import java.util.List;

import com.java.PlataformadeBlogs.dto.CommentDTO;

public interface CommentService {

	public CommentDTO createComment(Long postId, CommentDTO commentDTO);
	
	public List<CommentDTO> getAllCommentsById(Long postId);
	
	public CommentDTO getCommentById(Long postId, Long commentId);
	
	public CommentDTO updateComment(Long postId, Long commentId, CommentDTO requestComment);
	
	public void deleteComment(Long commentId);
}
