package com.sarvatra.controller;

import com.sarvatra.dto.PostDto;
import com.sarvatra.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/posts")
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getAllPosts();
    }

    @PostMapping
    public PostDto createNewPost(@RequestBody PostDto postDto) {
        return postService.createNewPost(postDto);
    }

    @GetMapping(path = "/{postId}")
    public PostDto getPostById(@PathVariable("postId") Long id) {
        return postService.getPostById(id);
    }

}
