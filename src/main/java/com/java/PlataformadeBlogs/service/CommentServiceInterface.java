package com.java.PlataformadeBlogs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.PlataformadeBlogs.dto.CommentDTO;
import com.java.PlataformadeBlogs.entities.Comment;
import com.java.PlataformadeBlogs.entities.Post;
import com.java.PlataformadeBlogs.repositories.CommentRepository;
import com.java.PlataformadeBlogs.repositories.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Service
public class CommentServiceInterface implements CommentService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
		
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found"));

        if (post.getInative() != 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse Post está inativo");
        }
        commentDTO.setId((long) 0);
        Comment comment = mapperEntity(commentDTO);

        comment.setPost(post);
        Comment newComment = commentRepository.save(comment);

        return mapperDTO(newComment);
	}

	@Override
	public List<CommentDTO> getAllCommentsById(Long postId) {
		List<Comment> comments = commentRepository.findCommentsByPostId(postId);
		
		return comments.stream().map(comment -> mapperDTO(comment)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentById(Long postId, Long commentId) {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		
		return mapperDTO(comment);
	}

	@Override
	public CommentDTO updateComment(Long postId, Long commentId, CommentDTO requestComment) {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		
		
		comment.setCommenter(requestComment.getCommenter());
		comment.setContent(requestComment.getContent());
		
		Comment commentCurrent = commentRepository.save(comment);		
		return mapperDTO(commentCurrent);
		
	}
	
	public void deleteComment(Long commentId) {
		
		Comment comment = commentRepository.findById(commentId).orElseThrow();
		
		
		commentRepository.delete(comment);
		
	}
	
	private CommentDTO mapperDTO(Comment comment) {
		CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);
		
		return commentDTO;
	}
	
	private Comment mapperEntity(CommentDTO commentDTO) {
		Comment comment = modelMapper.map(commentDTO, Comment.class);
		
		return comment;
	}

}
