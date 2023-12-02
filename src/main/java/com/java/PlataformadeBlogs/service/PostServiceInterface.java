package com.java.PlataformadeBlogs.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.java.PlataformadeBlogs.dto.PostDTO;
import com.java.PlataformadeBlogs.dto.PostResponse;
import com.java.PlataformadeBlogs.entities.Post;
import com.java.PlataformadeBlogs.repositories.PostRepository;

import jakarta.transaction.Transactional;

@Service
public class PostServiceInterface implements PostService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PostRepository postRepository;

	public PostResponse getAllPost(int numPage, int sizePage) {
		
		Pageable pageable = PageRequest.of(numPage, sizePage);

		Page<Post> publications = postRepository.findByInative(0, pageable);

		List<Post> listPublications = publications.getContent();

		List<PostDTO> content = listPublications.stream().map(publication -> mapperDTO(publication))
                .collect(Collectors.toList());

        PostResponse publicationResponse = new PostResponse();
        publicationResponse.setContentList(content);

        return publicationResponse;
	}

	@Override
	public PostDTO createPost(PostDTO postDTO) {
		postDTO.setId((long) 0);
		Post publication = mapperEntity(postDTO);

		Post newPublication = postRepository.save(publication);

		PostDTO publicationSave = mapperDTO(newPublication);		

		return publicationSave;
	}

	@Override
	public PostDTO findPostById(Long id) {
		Post publication = postRepository.findById(id)
				.orElseThrow();
		return mapperDTO(publication);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow();

		post.setTitle(postDTO.getTitle());
		post.setAuthor(postDTO.getAuthor());
		post.setContent(postDTO.getContent());
		post.setHashtags(postDTO.getHashtags());
		post.setInative(postDTO.getInative());

		Post updatedPublication = postRepository.save(post);

		return mapperDTO(updatedPublication);
	}

	@Override
	public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow();
        
        post.setInative(1);
        postRepository.save(post);
	}

	// Pass entity to DTO
	private PostDTO mapperDTO(Post publication) {
		PostDTO publicationDTO = modelMapper.map(publication, PostDTO.class);
		return publicationDTO;
	}

	// Pass DTO to entity
	private Post mapperEntity(PostDTO publicationDTO) {
		Post publication = modelMapper.map(publicationDTO, Post.class);
		return publication;
	}

    @Transactional
    @Override
    public PostDTO updatePostPartial(Long id, PostDTO partialPostDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow();

        // Atualize apenas as partes fornecidas no DTO parcial
        if (partialPostDTO.getTitle() != null) {
            post.setTitle(partialPostDTO.getTitle());
        }
        if (partialPostDTO.getAuthor() != null) {
            post.setAuthor(partialPostDTO.getAuthor());
        }
        if (partialPostDTO.getContent() != null) {
            post.setContent(partialPostDTO.getContent());
        }
        if (partialPostDTO.getHashtags() != null) {
            post.setHashtags(partialPostDTO.getHashtags());
        }
 

        Post updatedPost = postRepository.save(post);

        return mapperDTO(updatedPost);
    }

}
