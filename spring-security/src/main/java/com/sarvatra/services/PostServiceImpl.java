package com.sarvatra.services;

import com.sarvatra.dto.PostDTO;
import com.sarvatra.entities.PostEntity;
import com.sarvatra.repositories.PostRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> postDTOS = new ArrayList<>();
        for(PostEntity post : postRepository.findAll()) {
            PostDTO postDTO = new PostDTO(post.getId(), post.getTitle(), post.getDescription());
            postDTOS.add(postDTO);
        }

        return postDTOS;
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity1 = new PostEntity(inputPost.getId(), inputPost.getTitle(), inputPost.getDescription());
        postRepository.save(postEntity1);
        return inputPost;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        final PostEntity posts = postRepository.findById(postId).orElse(null);
        return new PostDTO(posts.getId(), posts.getTitle(), posts.getDescription());
    }
}
