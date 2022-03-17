package com.smoilin.io.springbootblog.repository;

import com.smoilin.io.springbootblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
