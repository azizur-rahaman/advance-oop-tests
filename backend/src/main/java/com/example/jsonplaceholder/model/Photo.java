package com.example.jsonplaceholder.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
public class Photo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "album_id")
    @JsonBackReference("album-photos")
    private Album album;
    
    @Column(length = 200)
    private String title;
    
    @Column(length = 255)
    private String url;
    
    @Column(name = "thumbnail_url", length = 255)
    private String thumbnailUrl;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public Photo() {
        this.createdAt = LocalDateTime.now();
    }
    
    public Photo(Album album, String title, String url, String thumbnailUrl) {
        this.album = album;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Album getAlbum() {
        return album;
    }
    
    public void setAlbum(Album album) {
        this.album = album;
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
