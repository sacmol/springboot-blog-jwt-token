package com.smoilin.io.springbootblog.controller;

import com.smoilin.io.springbootblog.dto.PostDto;
import com.smoilin.io.springbootblog.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@CrossOrigin("http://localhost:4200/")
public class Test {

    private final PostService postService;

    public Test(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> showAllPosts() {
        return new ResponseEntity<>(postService.showAllPosts(), HttpStatus.OK);
    }
}
