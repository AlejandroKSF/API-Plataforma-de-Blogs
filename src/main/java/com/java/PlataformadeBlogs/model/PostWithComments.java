package com.java.PlataformadeBlogs.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class PostWithComments {
    private Post post;
    private List<Comment> comments;

    public PostWithComments(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }

    // getters e setters
    public Post getPost() {
        return post;
    }
    @JsonIgnore
    public int getInative() { return this.post.getInative();}
    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
    
}