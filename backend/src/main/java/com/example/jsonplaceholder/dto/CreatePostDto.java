package com.example.jsonplaceholder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePostDto {
    
    @NotNull(message = "User ID is required")
    private Integer userId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "Body is required")
    private String body;
    
    // Constructors
    public CreatePostDto() {}
    
    public CreatePostDto(Integer userId, String title, String body) {
        this.userId = userId;
        this.title = title;
        this.body = body;
    }
    
    // Getters and Setters
    public Integer getUserId() {
        return userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getBody() {
        return body;
    }
    
    public void setBody(String body) {
        this.body = body;
    }
}
