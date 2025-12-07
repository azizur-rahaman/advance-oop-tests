package com.example.jsonplaceholder.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePhotoDto {
    
    @NotNull(message = "Album ID is required")
    private Integer albumId;
    
    @NotBlank(message = "Title is required")
    private String title;
    
    @NotBlank(message = "URL is required")
    private String url;
    
    private String thumbnailUrl;
    
    // Constructors
    public CreatePhotoDto() {}
    
    public CreatePhotoDto(Integer albumId, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }
    
    // Getters and Setters
    public Integer getAlbumId() {
        return albumId;
    }
    
    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }
    
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
