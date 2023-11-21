package com.java.PlataformadeBlogs.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.PlataformadeBlogs.model.Comment;
import com.java.PlataformadeBlogs.model.Patch;
import com.java.PlataformadeBlogs.model.Post;
import com.java.PlataformadeBlogs.model.PostWithComments;

@Service
public class PostService {
	  @Autowired
	    private CommentService commentService;
    private static List<Post> posts = new ArrayList<>();
    
    public PostService() {}
    
    public void create (Post post) {
    	post.setId(post.generateId());
    	posts.add(post);
    }
    
    public List<Post> findAll(){
        return posts.stream()
                .filter(post -> post.getInative() == 0)
                .collect(Collectors.toList());
    }
    
    public Post find(Post post) {
    	for (Post c : posts) {
    		if (c.equals(post)) {
    			return c;
    		}
    	}
    	return null;
    }
    
    
    public List<PostWithComments> findAllWithComments() {
        List<PostWithComments> postsWithComments = new ArrayList<>();
        for (Post post : posts) {
            List<Comment> postComments = commentService.findByPostId(post.getId());
            PostWithComments postWithComments = new PostWithComments(post, postComments);
            postsWithComments.add(postWithComments);
        }
        return postsWithComments.stream()
                .filter(post -> post.getInative() == 0)
                .collect(Collectors.toList());
    }

    public PostWithComments findWithComments(Long id) {
        Post post = find(id);
        if (post != null) {
            List<Comment> postComments = commentService.findByPostId(post.getId());
            return new PostWithComments(post, postComments);
        }
        return null;
    }

    public Post find(Long id) {
        return find(new Post(id));
    }
    
    public boolean exists(Long id) {
        return find(id) != null;
    }
    
    public boolean update(Post post) {
        Post _post = find(post);
        if (_post != null) {
            _post.setId(post.getId());
            _post.setTitle(post.getTitle());
            _post.setContent(post.getContent());
            _post.setAuthor(post.getAuthor());
            _post.setHashtags(post.getHashtags());
            _post.setInative(post.getInative());

            
            return true;
        }
        return false;
    }
    
    public Post patch(Long id, Patch patchRequest) {
        Post existingPost = find(id);

        if (existingPost != null) {
            // Aplicar modificações se os campos não forem nulos
            if (patchRequest.getTitle() != null) {
                existingPost.setTitle(patchRequest.getTitle());
            }
            if (patchRequest.getHashtags() != null) {
                existingPost.setHashtags(patchRequest.getHashtags());
            }
            if (patchRequest.getContent() != null) {
                existingPost.setContent(patchRequest.getContent());
            }
            return existingPost;
        }
        return null;
    }
    
    public boolean delete(Long id) {
        Post _post = find(id);
        if (_post != null) {
            _post.setInative(1);
            return true;
        }
        return false;
    }
}
