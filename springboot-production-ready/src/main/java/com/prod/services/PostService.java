package com.prod.services;

import com.prod.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PostService {

     public List<PostDto> getAllPosts();

     public PostDto getPostById(Long id);

     public PostDto updatePost(PostDto postDto ,Long id);

     public PostDto insertNewPostByID(PostDto newPostDto);
}