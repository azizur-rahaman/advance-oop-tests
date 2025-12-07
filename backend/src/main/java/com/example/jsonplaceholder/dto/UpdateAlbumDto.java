package com.example.jsonplaceholder.dto;

public class UpdateAlbumDto {
    
    private String title;
    
    // Constructors
    public UpdateAlbumDto() {}
    
    public UpdateAlbumDto(String title) {
        this.title = title;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
}
