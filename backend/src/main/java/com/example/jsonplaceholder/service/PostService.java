package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.Post;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.repository.PostRepository;
import com.example.jsonplaceholder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    
    @Autowired
    private PostRepository postRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // GET all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    
    // GET post by ID
    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }
    
    // GET posts by user ID
    public List<Post> getPostsByUserId(Integer userId) {
        return postRepository.findByUserId(userId);
    }
    
    // POST - Create new post
    public Post createPost(Post post) {
        if (post.getUser() != null && post.getUser().getId() != null) {
            User user = userRepository.findById(post.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + post.getUser().getId()));
            post.setUser(user);
        }
        return postRepository.save(post);
    }
    
    // PUT - Update entire post
    public Post updatePost(Integer id, Post postDetails) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        
        if (postDetails.getUser() != null && postDetails.getUser().getId() != null) {
            User user = userRepository.findById(postDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + postDetails.getUser().getId()));
            post.setUser(user);
        }
        post.setTitle(postDetails.getTitle());
        post.setBody(postDetails.getBody());
        
        return postRepository.save(post);
    }
    
    // PATCH - Partially update post
    public Post patchPost(Integer id, Post postDetails) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        
        if (postDetails.getUser() != null && postDetails.getUser().getId() != null) {
            User user = userRepository.findById(postDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + postDetails.getUser().getId()));
            post.setUser(user);
        }
        if (postDetails.getTitle() != null) {
            post.setTitle(postDetails.getTitle());
        }
        if (postDetails.getBody() != null) {
            post.setBody(postDetails.getBody());
        }
        
        return postRepository.save(post);
    }
    
    // DELETE post
    public void deletePost(Integer id) {
        Post post = postRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Post not found with id: " + id));
        postRepository.delete(post);
    }
}
