package com.example.jsonplaceholder.dto;

import java.time.LocalDateTime;

public class PhotoResponseDto {
    
    private Integer id;
    private Integer albumId;
    private String title;
    private String url;
    private String thumbnailUrl;
    private LocalDateTime createdAt;
    
    // Constructors
    public PhotoResponseDto() {}
    
    public PhotoResponseDto(Integer id, Integer albumId, String title, String url, String thumbnailUrl, LocalDateTime createdAt) {
        this.id = id;
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
