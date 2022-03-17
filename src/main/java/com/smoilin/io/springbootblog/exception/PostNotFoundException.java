package com.smoilin.io.springbootblog.exception;

public class PostNotFoundException extends RuntimeException{

    public PostNotFoundException(String message) {
        super(message);
    }
}
