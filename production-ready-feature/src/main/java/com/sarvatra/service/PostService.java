package com.sarvatra.service;

import com.sarvatra.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto createNewPost(PostDto inputPost);
    PostDto getPostById(Long id);
}
