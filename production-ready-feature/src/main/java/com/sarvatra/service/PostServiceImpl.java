package com.sarvatra.service;

import com.sarvatra.dto.PostDto;
import com.sarvatra.entities.PostEntity;
import com.sarvatra.exceptions.ResourceNotFoundException;
import com.sarvatra.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<PostDto> getAllPosts() {
        List<PostDto> posts = new ArrayList<>();
        final List<PostEntity> allPosts = postRepository.findAll();
        for (PostEntity post : allPosts) {
            PostDto postDto = new PostDto(post.getId(), post.getTitle(), post.getDescription());
            posts.add(postDto);
        }

        return posts;
    }

    @Override
    public PostDto createNewPost(PostDto inputPost) {
        PostEntity post = PostEntity.builder()
                .title(inputPost.getTitle())
                .description(inputPost.getDescription())
                .build();

        postRepository.save(post);
        return inputPost;
    }

    @Override
    public PostDto getPostById(Long id) {
        PostEntity post=  postRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Post with id = " + id + " NOT FOUND !!!"));
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .build();

    }
}
