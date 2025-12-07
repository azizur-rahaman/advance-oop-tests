package com.example.jsonplaceholder.dto;

import javax.validation.constraints.Email;

public class UpdateCommentDto {
    
    private String name;
    
    @Email(message = "Email should be valid")
    private String email;
    
    private String body;
    
    // Constructors
    public UpdateCommentDto() {}
    
    public UpdateCommentDto(String name, String email, String body) {
        this.name = name;
        this.email = email;
        this.body = body;
    }
    
    // Getters and Setters
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
}
