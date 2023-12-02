package com.java.PlataformadeBlogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.PlataformadeBlogs.dto.CommentDTO;
import com.java.PlataformadeBlogs.service.CommentService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	@GetMapping("/posts/{postId}/comments")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna a lista de todos os comentários de acordo com o post"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public List<CommentDTO> listCommentsByPostId(@PathVariable(value = "postId") Long postId) {
		return commentService.getAllCommentsById(postId);
	}

	@GetMapping("/comments/{commentId}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
					     description = "Comentário pelo id"),
			@ApiResponse(responseCode = "404", 
			             description = "Comentário não encontrado"),
			@ApiResponse(responseCode = "500", 
			             description = "Erro interno do sistema"), })
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable(value = "postId") Long postId,
			@PathVariable(value = "commentId") Long commentId) {
		
		CommentDTO commentDTO = commentService.getCommentById(postId, commentId);
		return new ResponseEntity<>(commentDTO, HttpStatus.OK);
	}

	@PostMapping("/posts/{postId}/comments")
	@ResponseStatus(HttpStatus.CREATED)
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Comentário criado"),
			@ApiResponse(responseCode = "500", description = "O post não existe"), })
	public ResponseEntity<CommentDTO> saveComment(@PathVariable(value = "postId") Long postId,
			@RequestBody CommentDTO commentDTO) {
		
		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
	}

	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Comentário excluído"),
			@ApiResponse(responseCode = "404", description = "Comentário não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<String> deleteComment(
			@PathVariable(value = "commentId") Long commentId) {
		
		commentService.deleteComment(commentId);
		return new ResponseEntity<>("Comentário excluido", HttpStatus.OK);
	}

}
