package com.example.jsonplaceholder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateTodoDto {
    
    @NotNull(message = "User ID is required")
    private Integer userId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    private Boolean completed;
    
    // Constructors
    public CreateTodoDto() {}
    
    public CreateTodoDto(Integer userId, String title, Boolean completed) {
        this.userId = userId;
        this.title = title;
        this.completed = completed;
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
    
    public Boolean getCompleted() {
        return completed;
    }
    
    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
