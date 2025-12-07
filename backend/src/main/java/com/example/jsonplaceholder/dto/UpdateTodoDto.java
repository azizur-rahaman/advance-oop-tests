package com.example.jsonplaceholder.dto;

public class UpdateTodoDto {
    
    private String title;
    private Boolean completed;
    
    // Constructors
    public UpdateTodoDto() {}
    
    public UpdateTodoDto(String title, Boolean completed) {
        this.title = title;
        this.completed = completed;
    }
    
    // Getters and Setters
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
