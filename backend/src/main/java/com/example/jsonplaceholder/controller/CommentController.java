package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.dto.CreateCommentDto;
import com.example.jsonplaceholder.dto.UpdateCommentDto;
import com.example.jsonplaceholder.dto.CommentResponseDto;
import com.example.jsonplaceholder.mapper.DtoMapper;
import com.example.jsonplaceholder.model.Comment;
import com.example.jsonplaceholder.model.Post;
import com.example.jsonplaceholder.service.CommentService;
import com.example.jsonplaceholder.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private PostService postService;
    
    @Autowired
    private DtoMapper dtoMapper;
    
    // GET all comments
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getAllComments(@RequestParam(required = false) Integer postId) {
        List<Comment> comments;
        if (postId != null) {
            comments = commentService.getCommentsByPostId(postId);
        } else {
            comments = commentService.getAllComments();
        }
        List<CommentResponseDto> commentDtos = comments.stream()
            .map(dtoMapper::toCommentResponseDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(commentDtos, HttpStatus.OK);
    }
    
    // GET comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id)
            .map(comment -> new ResponseEntity<>(dtoMapper.toCommentResponseDto(comment), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new comment
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CreateCommentDto createCommentDto) {
        try {
            Post post = postService.getPostById(createCommentDto.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));
            
            Comment comment = new Comment();
            comment.setPost(post);
            comment.setName(createCommentDto.getName());
            comment.setEmail(createCommentDto.getEmail());
            comment.setBody(createCommentDto.getBody());
            
            Comment createdComment = commentService.createComment(comment);
            return new ResponseEntity<>(dtoMapper.toCommentResponseDto(createdComment), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire comment
    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Integer id, @Valid @RequestBody UpdateCommentDto updateCommentDto) {
        try {
            Comment existingComment = commentService.getCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
            
            existingComment.setName(updateCommentDto.getName());
            existingComment.setEmail(updateCommentDto.getEmail());
            existingComment.setBody(updateCommentDto.getBody());
            
            Comment updatedComment = commentService.updateComment(id, existingComment);
            return new ResponseEntity<>(dtoMapper.toCommentResponseDto(updatedComment), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update comment
    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> patchComment(@PathVariable Integer id, @RequestBody UpdateCommentDto updateCommentDto) {
        try {
            Comment existingComment = commentService.getCommentById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
            
            dtoMapper.updateCommentFromDto(existingComment, updateCommentDto);
            Comment patchedComment = commentService.patchComment(id, existingComment);
            return new ResponseEntity<>(dtoMapper.toCommentResponseDto(patchedComment), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE comment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Integer id) {
        try {
            commentService.deleteComment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
