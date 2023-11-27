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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.HttpStatus;
import com.java.PlataformadeBlogs.model.Comment;
import com.java.PlataformadeBlogs.service.CommentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	private CommentService service;

	@GetMapping
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os comentários"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<List<Comment>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}", produces = "application/json")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
					     description = "Comentário pelo id"),
			@ApiResponse(responseCode = "404", 
			             description = "Comentário não encontrada"),
			@ApiResponse(responseCode = "500", 
			             description = "Erro interno do sistema"), })
	public ResponseEntity<Comment> get(@PathVariable("id") Long id) {
		Comment _Comment = service.find(id);
		if (_Comment != null)
			return ResponseEntity.ok(_Comment);
		return ResponseEntity.notFound().build();
	}
	
    @GetMapping(value = "/post/{postId}" , produces = "application/json")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
					     description = "Comentários do Post solicitado"),
			@ApiResponse(responseCode = "404", 
			             description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", 
			             description = "Erro interno do sistema"), })
    public ResponseEntity<List<Comment>> getByPostId(@PathVariable("postId") Long postId) {
        List<Comment> comments = service.findByPostId(postId);
        return ResponseEntity.ok(comments);
    }

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Comentário criado"),
			@ApiResponse(responseCode = "500", description = "O post não existe"), })
	public ResponseEntity<Comment> Comment(@RequestBody Comment Comment) {
		service.create(Comment);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Comment.getId())
				.toUri();
		return ResponseEntity.created(location).body(Comment);
	}
	
	@DeleteMapping(value = "/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Comentário excluído"),
			@ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
	
}
