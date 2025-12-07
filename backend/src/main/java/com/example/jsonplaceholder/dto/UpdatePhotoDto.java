package com.example.jsonplaceholder.dto;

public class UpdatePhotoDto {
    
    private String title;
    private String url;
    private String thumbnailUrl;
    
    // Constructors
    public UpdatePhotoDto() {}
    
    public UpdatePhotoDto(String title, String url, String thumbnailUrl) {
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
    
    // Getters and Setters
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    
    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
