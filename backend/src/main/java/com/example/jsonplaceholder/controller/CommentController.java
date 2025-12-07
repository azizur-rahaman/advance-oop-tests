package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.model.Comment;
import com.example.jsonplaceholder.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {
    
    @Autowired
    private CommentService commentService;
    
    // GET all comments
    @GetMapping
    public ResponseEntity<List<Comment>> getAllComments(@RequestParam(required = false) Integer postId) {
        List<Comment> comments;
        if (postId != null) {
            comments = commentService.getCommentsByPostId(postId);
        } else {
            comments = commentService.getAllComments();
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    
    // GET comment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Integer id) {
        return commentService.getCommentById(id)
            .map(comment -> new ResponseEntity<>(comment, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new comment
    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment) {
        try {
            Comment createdComment = commentService.createComment(comment);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire comment
    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Integer id, @RequestBody Comment comment) {
        try {
            Comment updatedComment = commentService.updateComment(id, comment);
            return new ResponseEntity<>(updatedComment, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update comment
    @PatchMapping("/{id}")
    public ResponseEntity<Comment> patchComment(@PathVariable Integer id, @RequestBody Comment comment) {
        try {
            Comment patchedComment = commentService.patchComment(id, comment);
            return new ResponseEntity<>(patchedComment, HttpStatus.OK);
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
