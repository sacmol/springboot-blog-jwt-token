package com.smoilin.io.springbootblog.controller;

import com.smoilin.io.springbootblog.dto.PostDto;
import com.smoilin.io.springbootblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin("http://localhost:4200")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity createPost(@RequestBody PostDto postDto) {

        postService.createPost(postDto);
        return new ResponseEntity(HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PostDto> getSinglePost(@PathVariable @RequestBody Long id) {
        return new ResponseEntity<>(postService.readSinglePost(id), HttpStatus.OK);
    }
}
