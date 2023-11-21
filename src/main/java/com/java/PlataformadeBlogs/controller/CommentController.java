package com.java.PlataformadeBlogs.controller;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.java.PlataformadeBlogs.model.Comment;
import com.java.PlataformadeBlogs.service.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private CommentService service;

	@GetMapping
	public ResponseEntity<List<Comment>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public ResponseEntity<Comment> get(@PathVariable("id") Long id) {
		Comment _Comment = service.find(id);
		if (_Comment != null)
			return ResponseEntity.ok(_Comment);
		return ResponseEntity.notFound().build();
	}
	
    @GetMapping(value = "/post/{postId}" , produces = "application/json")
    public ResponseEntity<List<Comment>> getByPostId(@PathVariable("postId") Long postId) {
        List<Comment> comments = service.findByPostId(postId);
        return ResponseEntity.ok(comments);
    }

	@PostMapping
	public ResponseEntity<Comment> Comment(@RequestBody Comment Comment) {
		service.create(Comment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Comment.getId())
				.toUri();
		return ResponseEntity.created(location).body(Comment);
	}


	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
