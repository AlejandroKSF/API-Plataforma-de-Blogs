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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import com.java.PlataformadeBlogs.model.Patch;
import com.java.PlataformadeBlogs.model.Post;
import com.java.PlataformadeBlogs.model.PostWithComments;
import com.java.PlataformadeBlogs.service.PostService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/posts")
public class PostController {
	@Autowired
	private PostService service;


    @GetMapping
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna a lista Posts"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
    public ResponseEntity<List<PostWithComments>> getAll() {
        List<PostWithComments> postsWithComments = service.findAllWithComments();
        return ResponseEntity.ok(postsWithComments);
    }

    @GetMapping(value = "/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
					     description = "Post pelo id"),
			@ApiResponse(responseCode = "404", 
			             description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", 
			             description = "Erro interno do sistema"), })
    public ResponseEntity<PostWithComments> get(@PathVariable("id") Long id) {
        PostWithComments postWithComments = service.findWithComments(id);
        if (postWithComments != null)
            return ResponseEntity.ok(postWithComments);
        return ResponseEntity.notFound().build();
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Post criado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<Post> post(@RequestBody Post post) {
		service.create(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).body(post);
	}

	@PutMapping(value = "/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Post atualizada"),
			@ApiResponse(responseCode = "404", description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<Post> put(@RequestBody Post post) {
		if (service.update(post)) {
			return ResponseEntity.ok(post);
		}
		return ResponseEntity.notFound().build();
	}
	
    @PatchMapping(value = "/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Post atualizada"),
			@ApiResponse(responseCode = "404", description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
    public ResponseEntity<Post> patch(@PathVariable("id") Long id, @RequestBody Patch patchRequest) {
        Post updatedPost = service.patch(id, patchRequest);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
	@DeleteMapping(value = "/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Post excluído"),
			@ApiResponse(responseCode = "404", description = "Post não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
