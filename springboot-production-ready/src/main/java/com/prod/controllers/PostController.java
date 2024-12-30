package com.prod.controllers;

import com.prod.dto.PostDto;
import com.prod.services.PostService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPost(){
        return postService.getAllPosts();
    }

    @GetMapping(path = "/{postId}")
    public PostDto getPostById(@PathVariable Long postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public PostDto insertNewPost(@RequestBody PostDto postDto){
        return postService.insertNewPostByID(postDto);
    }

    @PutMapping(path = "/{postId}")
    public PostDto updatePostById(@RequestBody PostDto postDto, @PathVariable Long postId){
        return postService.updatePost(postDto, postId);
    }


}
