package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.model.Post;
import com.example.jsonplaceholder.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    // GET all posts
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(required = false) Integer userId) {
        List<Post> posts;
        if (userId != null) {
            posts = postService.getPostsByUserId(userId);
        } else {
            posts = postService.getAllPosts();
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    
    // GET post by ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id)
            .map(post -> new ResponseEntity<>(post, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        try {
            Post createdPost = postService.createPost(post);
            return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire post
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Integer id, @RequestBody Post post) {
        try {
            Post updatedPost = postService.updatePost(id, post);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update post
    @PatchMapping("/{id}")
    public ResponseEntity<Post> patchPost(@PathVariable Integer id, @RequestBody Post post) {
        try {
            Post patchedPost = postService.patchPost(id, post);
            return new ResponseEntity<>(patchedPost, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Integer id) {
        try {
            postService.deletePost(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
