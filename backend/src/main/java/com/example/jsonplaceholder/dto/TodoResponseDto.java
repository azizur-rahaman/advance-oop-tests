package com.example.jsonplaceholder.dto;

import java.time.LocalDateTime;

public class TodoResponseDto {
    
    private Integer id;
    private Integer userId;
    private String title;
    private Boolean completed;
    private LocalDateTime createdAt;
    
    // Constructors
    public TodoResponseDto() {}
    
    public TodoResponseDto(Integer id, Integer userId, String title, Boolean completed, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
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
    
    public Boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
