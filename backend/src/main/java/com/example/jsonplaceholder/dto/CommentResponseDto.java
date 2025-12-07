package com.example.jsonplaceholder.dto;

import java.time.LocalDateTime;

public class CommentResponseDto {
    
    private Integer id;
    private Integer postId;
    private String name;
    private String email;
    private String body;
    private LocalDateTime createdAt;
    
    // Constructors
    public CommentResponseDto() {}
    
    public CommentResponseDto(Integer id, Integer postId, String name, String email, String body, LocalDateTime createdAt) {
        this.id = id;
        this.postId = postId;
        this.name = name;
        this.email = email;
        this.body = body;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getPostId() {
        return postId;
    }
    
    public void setPostId(Integer postId) {
        this.postId = postId;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
