package com.java.PlataformadeBlogs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.java.PlataformadeBlogs.dto.PostDTO;
import com.java.PlataformadeBlogs.dto.PostResponse;
import com.java.PlataformadeBlogs.service.PostService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Retorna a lista de Posts"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	@ResponseStatus(HttpStatus.OK)
	public PostResponse listPost(
			@RequestParam(value = "pageNo", defaultValue = "0", required = false) int numPage,
			@RequestParam(value = "sizePage", defaultValue = "5", required = false) int sizePage){
		return postService.getAllPost(numPage, sizePage);
	}
	
	@GetMapping("/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", 
					     description = "Post pelo id"),
			@ApiResponse(responseCode = "404", 
			             description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", 
			             description = "Erro interno do sistema"), })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PostDTO> getPost(@Valid @PathVariable(value= "id") Long id){
		return ResponseEntity.ok(postService.findPostById(id));
	}
	
	@PostMapping
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "201", description = "Post criado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<PostDTO> savePost(@Valid @RequestBody PostDTO publicationDTO){
		return new ResponseEntity<>(postService.createPost(publicationDTO), HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Post atualizado"),
			@ApiResponse(responseCode = "404", description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<PostDTO> updatePost(@Valid @RequestBody PostDTO publicationDTO, @PathVariable(value= "id") Long id){
		PostDTO publicationResponse = postService.updatePost(publicationDTO, id);
		
		return new ResponseEntity<>(publicationResponse, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Post excluído"),
			@ApiResponse(responseCode = "404", description = "Post  não encontrada"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
	public ResponseEntity<String> deletePost(@PathVariable(value = "id") Long id){
		postService.deletePost(id);
		
		return new ResponseEntity<String>("Post deletado!", HttpStatus.OK);
	}
	
	@PatchMapping("/{id}")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Post atualizado"),
			@ApiResponse(responseCode = "404", description = "Post não encontrado"),
			@ApiResponse(responseCode = "500", description = "Erro interno do sistema"), })
    public ResponseEntity<PostDTO> patchPost(@Valid @RequestBody PostDTO patchPostDTO, @PathVariable(value = "id") Long id) {
        PostDTO updatedPostDTO = postService.updatePostPartial(id, patchPostDTO);
        return ResponseEntity.ok(updatedPostDTO);
    }
	
}
