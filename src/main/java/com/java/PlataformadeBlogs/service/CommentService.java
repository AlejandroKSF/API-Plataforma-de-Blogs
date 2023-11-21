package com.java.PlataformadeBlogs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.PlataformadeBlogs.model.Comment;

@Service 
public class CommentService {
    @Autowired
    private PostService postService;
    private final List<Comment> comments = new ArrayList<>();
    

    
    public void create (Comment comment) {
        if (postService.exists(comment.getPostId())) { // Verifica se o post existe
            comment.setId(comment.generateId());
            comments.add(comment);
        } else {
            throw new IllegalArgumentException("O Post de ID " + comment.getPostId() + " não existe.");
        }
    }
    
    public List<Comment> findAll(){
    	return comments;
    }
    
    public Comment find(Comment comment) {
    	for (Comment c : comments) {
    		if (c.equals(comment)) {
    			return c;
    		}
    	}
    	return null;
    }
    
    public List<Comment> findByPostId(Long postId) {
        // Filtrar os comentários pelo postId
        return comments.stream()
                .filter(comment -> comment.getPostId().equals(postId))
                .collect(Collectors.toList());
    }
    
    public Comment find(Long id) {
        return find(new Comment(id));
    }
    
    public boolean delete(Long id) {
        Comment _comment = find(id);
        if (_comment != null) {
            comments.remove(_comment);
            return true;
        }
        return false;
    }
}
