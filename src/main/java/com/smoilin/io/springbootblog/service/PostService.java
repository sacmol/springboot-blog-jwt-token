package com.smoilin.io.springbootblog.service;

import com.smoilin.io.springbootblog.dto.PostDto;
import com.smoilin.io.springbootblog.exception.PostNotFoundException;
import com.smoilin.io.springbootblog.model.Post;
import com.smoilin.io.springbootblog.repository.PostRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {

    private AuthService authService;
    private PostRepository postRepository;

    public PostService(AuthService authService, PostRepository postRepository) {
        this.authService = authService;
        this.postRepository = postRepository;
    }

    public List<PostDto> showAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream().map(this::mapFromPostToDto).collect(Collectors.toList());
    }

    private PostDto mapFromPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setUsername(post.getUsername());
        return postDto;
    }

    public void createPost(PostDto postDto) {

        Post post = mapFromDtoToPost(postDto);
        postRepository.save(post);
    }
    private Post mapFromDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        User loggedInUser = authService.getCurrentUser().orElseThrow(() -> new IllegalArgumentException("User Not Found"));
        post.setCreatedOn(Instant.now());
        post.setUsername(loggedInUser.getUsername());
        post.setUpdatedOn(Instant.now());
        return post;
    }

    public PostDto readSinglePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException("For id " + id));
        return mapFromPostToDto(post);
    }

}
