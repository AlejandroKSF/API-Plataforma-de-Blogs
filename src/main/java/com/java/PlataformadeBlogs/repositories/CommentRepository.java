package com.java.PlataformadeBlogs.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.PlataformadeBlogs.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	public List<Comment> findCommentsByPostId(Long postId);
}
