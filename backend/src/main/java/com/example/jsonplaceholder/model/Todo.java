package com.example.jsonplaceholder.model;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import java.time.LocalDateTime;

@Entity
@Table(name = "todos")
public class Todo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference("user-todos")
    private User user;
    
    @Column(length = 200)
    private String title;
    
    private Boolean completed;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    // Constructors
    public Todo() {
        this.completed = false;
        this.createdAt = LocalDateTime.now();
    }
    
    public Todo(User user, String title, Boolean completed) {
        this.user = user;
        this.title = title;
        this.completed = completed != null ? completed : false;
        this.createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
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
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
