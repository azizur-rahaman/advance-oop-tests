package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.dto.CreatePostDto;
import com.example.jsonplaceholder.dto.UpdatePostDto;
import com.example.jsonplaceholder.dto.PostResponseDto;
import com.example.jsonplaceholder.mapper.DtoMapper;
import com.example.jsonplaceholder.model.Post;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.service.PostService;
import com.example.jsonplaceholder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DtoMapper dtoMapper;
    
    // GET all posts
    @GetMapping
    public ResponseEntity<List<PostResponseDto>> getAllPosts(@RequestParam(required = false) Integer userId) {
        List<Post> posts;
        if (userId != null) {
            posts = postService.getPostsByUserId(userId);
        } else {
            posts = postService.getAllPosts();
        }
        List<PostResponseDto> postDtos = posts.stream()
            .map(dtoMapper::toPostResponseDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(postDtos, HttpStatus.OK);
    }
    
    // GET post by ID
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Integer id) {
        return postService.getPostById(id)
            .map(post -> new ResponseEntity<>(dtoMapper.toPostResponseDto(post), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new post
    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody CreatePostDto createPostDto) {
        try {
            User user = userService.getUserById(createPostDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            Post post = new Post();
            post.setUser(user);
            post.setTitle(createPostDto.getTitle());
            post.setBody(createPostDto.getBody());
            
            Post createdPost = postService.createPost(post);
            return new ResponseEntity<>(dtoMapper.toPostResponseDto(createdPost), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire post
    @PutMapping("/{id}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Integer id, @Valid @RequestBody UpdatePostDto updatePostDto) {
        try {
            Post existingPost = postService.getPostById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
            
            existingPost.setTitle(updatePostDto.getTitle());
            existingPost.setBody(updatePostDto.getBody());
            
            Post updatedPost = postService.updatePost(id, existingPost);
            return new ResponseEntity<>(dtoMapper.toPostResponseDto(updatedPost), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update post
    @PatchMapping("/{id}")
    public ResponseEntity<PostResponseDto> patchPost(@PathVariable Integer id, @RequestBody UpdatePostDto updatePostDto) {
        try {
            Post existingPost = postService.getPostById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
            
            dtoMapper.updatePostFromDto(existingPost, updatePostDto);
            Post patchedPost = postService.patchPost(id, existingPost);
            return new ResponseEntity<>(dtoMapper.toPostResponseDto(patchedPost), HttpStatus.OK);
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
