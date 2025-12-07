package com.example.jsonplaceholder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateAlbumDto {
    
    @NotNull(message = "User ID is required")
    private Integer userId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    // Constructors
    public CreateAlbumDto() {}
    
    public CreateAlbumDto(Integer userId, String title) {
        this.userId = userId;
        this.title = title;
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
}
