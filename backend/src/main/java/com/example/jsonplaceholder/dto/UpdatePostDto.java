package com.example.jsonplaceholder.dto;

public class UpdatePostDto {
    
    private String title;
    private String body;
    
    // Constructors
    public UpdatePostDto() {}
    
    public UpdatePostDto(String title, String body) {
        this.title = title;
        this.body = body;
    }
    
    // Getters and Setters
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
