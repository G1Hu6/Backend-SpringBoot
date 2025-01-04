package com.security.services.impl;

import com.security.dto.PostDto;
import com.security.entities.PostEntity;
import com.security.exceptions.ResourceNotFoundException;
import com.security.repositories.PostRepository;
import com.security.services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDto.class) )
                .toList();
    }

    @Override
    public PostDto getPostById(Long id) {
        return modelMapper.map(postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + id)), PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto toUpdateDto, Long id) {
        PostEntity oldPost = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post not found with id : " + id));
        toUpdateDto.setId(id);
        modelMapper.map(toUpdateDto, oldPost);
        return modelMapper.map(postRepository.save(oldPost), PostDto.class);
    }

    @Override
    public PostDto insertNewPostByID(PostDto newPostDto) {
        PostEntity newPostEntity = modelMapper.map(newPostDto, PostEntity.class);
        return modelMapper.map(postRepository.save(newPostEntity), PostDto.class);
    }
}
