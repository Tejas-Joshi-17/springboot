package com.sarvatra.services;

import com.sarvatra.dto.PostDTO;
import com.sarvatra.dto.UserDto;
import com.sarvatra.entities.PostEntity;
import com.sarvatra.entities.User;
import com.sarvatra.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOS = new ArrayList<>();
        for(PostEntity post : postRepository.findAll()) {
            PostDTO postDTO = new PostDTO(post.getId(), post.getTitle(), post.getDescription(),
                    new UserDto(post.getAuthor().getId(), post.getAuthor().getName(), post.getAuthor().getEmail()));
            postDTOS.add(postDTO);
        }

        return postDTOS;
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        PostEntity postEntity1 = new PostEntity(inputPost.getId(), inputPost.getTitle(), inputPost.getDescription(), user);
        postRepository.save(postEntity1);
        return inputPost;
    }

    @Override
    public PostDTO getPostById(Long postId) {

        // TODO Just for checking
        // User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // log.info("user data is :- {}", user);

        final PostEntity posts = postRepository.findById(postId).orElse(null);
        return new PostDTO(posts.getId(), posts.getTitle(), posts.getDescription(),
                new UserDto(posts.getAuthor().getId(), posts.getAuthor().getName(), posts.getAuthor().getEmail()));
    }
}
