package com.java.PlataformadeBlogs.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.PlataformadeBlogs.model.Patch;
import com.java.PlataformadeBlogs.model.Post;
import com.java.PlataformadeBlogs.model.PostWithComments;
import com.java.PlataformadeBlogs.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService service;


    @GetMapping
    public ResponseEntity<List<PostWithComments>> getAll() {
        List<PostWithComments> postsWithComments = service.findAllWithComments();
        return ResponseEntity.ok(postsWithComments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostWithComments> get(@PathVariable("id") Long id) {
        PostWithComments postWithComments = service.findWithComments(id);
        if (postWithComments != null)
            return ResponseEntity.ok(postWithComments);
        return ResponseEntity.notFound().build();
    }

	@PostMapping
	public ResponseEntity<Post> post(@RequestBody Post post) {
		service.create(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).body(post);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Post> put(@RequestBody Post post) {
		if (service.update(post)) {
			return ResponseEntity.ok(post);
		}
		return ResponseEntity.notFound().build();
	}
	
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Post> patch(@PathVariable("id") Long id, @RequestBody Patch patchRequest) {
        Post updatedPost = service.patch(id, patchRequest);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
