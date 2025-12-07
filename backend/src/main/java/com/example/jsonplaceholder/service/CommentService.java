package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.Comment;
import com.example.jsonplaceholder.model.Post;
import com.example.jsonplaceholder.repository.CommentRepository;
import com.example.jsonplaceholder.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private PostRepository postRepository;
    
    // GET all comments
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }
    
    // GET comment by ID
    public Optional<Comment> getCommentById(Integer id) {
        return commentRepository.findById(id);
    }
    
    // GET comments by post ID
    public List<Comment> getCommentsByPostId(Integer postId) {
        return commentRepository.findByPostId(postId);
    }
    
    // POST - Create new comment
    public Comment createComment(Comment comment) {
        if (comment.getPost() != null && comment.getPost().getId() != null) {
            Post post = postRepository.findById(comment.getPost().getId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + comment.getPost().getId()));
            comment.setPost(post);
        }
        return commentRepository.save(comment);
    }
    
    // PUT - Update entire comment
    public Comment updateComment(Integer id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        
        if (commentDetails.getPost() != null && commentDetails.getPost().getId() != null) {
            Post post = postRepository.findById(commentDetails.getPost().getId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + commentDetails.getPost().getId()));
            comment.setPost(post);
        }
        comment.setName(commentDetails.getName());
        comment.setEmail(commentDetails.getEmail());
        comment.setBody(commentDetails.getBody());
        
        return commentRepository.save(comment);
    }
    
    // PATCH - Partially update comment
    public Comment patchComment(Integer id, Comment commentDetails) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        
        if (commentDetails.getPost() != null && commentDetails.getPost().getId() != null) {
            Post post = postRepository.findById(commentDetails.getPost().getId())
                .orElseThrow(() -> new RuntimeException("Post not found with id: " + commentDetails.getPost().getId()));
            comment.setPost(post);
        }
        if (commentDetails.getName() != null) {
            comment.setName(commentDetails.getName());
        }
        if (commentDetails.getEmail() != null) {
            comment.setEmail(commentDetails.getEmail());
        }
        if (commentDetails.getBody() != null) {
            comment.setBody(commentDetails.getBody());
        }
        
        return commentRepository.save(comment);
    }
    
    // DELETE comment
    public void deleteComment(Integer id) {
        Comment comment = commentRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Comment not found with id: " + id));
        commentRepository.delete(comment);
    }
}
