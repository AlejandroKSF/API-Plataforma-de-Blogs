package com.java.PlataformadeBlogs.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.PlataformadeBlogs.entities.Post;

public interface PostRepository extends JpaRepository<Post, Long>{

	Page<Post> findByInative(int i, Pageable pageable);

}
