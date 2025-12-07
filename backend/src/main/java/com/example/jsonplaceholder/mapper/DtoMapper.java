package com.example.jsonplaceholder.mapper;

import com.example.jsonplaceholder.dto.*;
import com.example.jsonplaceholder.model.*;
import org.springframework.stereotype.Component;

@Component
public class DtoMapper {
    
    // ==================== USER MAPPERS ====================
    
    public User toUser(CreateUserDto dto) {
        User user = new User();
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setWebsite(dto.getWebsite());
        return user;
    }
    
    public void updateUserFromDto(User user, UpdateUserDto dto) {
        if (dto.getName() != null) {
            user.setName(dto.getName());
        }
        if (dto.getUsername() != null) {
            user.setUsername(dto.getUsername());
        }
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getWebsite() != null) {
            user.setWebsite(dto.getWebsite());
        }
    }
    
    public UserResponseDto toUserResponseDto(User user) {
        return new UserResponseDto(
            user.getId(),
            user.getName(),
            user.getUsername(),
            user.getEmail(),
            user.getPhone(),
            user.getWebsite()
        );
    }
    
    // ==================== POST MAPPERS ====================
    
    public PostResponseDto toPostResponseDto(Post post) {
        return new PostResponseDto(
            post.getId(),
            post.getUser() != null ? post.getUser().getId() : null,
            post.getTitle(),
            post.getBody(),
            post.getCreatedAt(),
            post.getUpdatedAt()
        );
    }
    
    public void updatePostFromDto(Post post, UpdatePostDto dto) {
        if (dto.getTitle() != null) {
            post.setTitle(dto.getTitle());
        }
        if (dto.getBody() != null) {
            post.setBody(dto.getBody());
        }
    }
    
    // ==================== TODO MAPPERS ====================
    
    public TodoResponseDto toTodoResponseDto(Todo todo) {
        return new TodoResponseDto(
            todo.getId(),
            todo.getUser() != null ? todo.getUser().getId() : null,
            todo.getTitle(),
            todo.getCompleted(),
            todo.getCreatedAt()
        );
    }
    
    public void updateTodoFromDto(Todo todo, UpdateTodoDto dto) {
        if (dto.getTitle() != null) {
            todo.setTitle(dto.getTitle());
        }
        if (dto.getCompleted() != null) {
            todo.setCompleted(dto.getCompleted());
        }
    }
    
    // ==================== COMMENT MAPPERS ====================
    
    public CommentResponseDto toCommentResponseDto(Comment comment) {
        return new CommentResponseDto(
            comment.getId(),
            comment.getPost() != null ? comment.getPost().getId() : null,
            comment.getName(),
            comment.getEmail(),
            comment.getBody(),
            comment.getCreatedAt()
        );
    }
    
    public void updateCommentFromDto(Comment comment, UpdateCommentDto dto) {
        if (dto.getName() != null) {
            comment.setName(dto.getName());
        }
        if (dto.getEmail() != null) {
            comment.setEmail(dto.getEmail());
        }
        if (dto.getBody() != null) {
            comment.setBody(dto.getBody());
        }
    }
    
    // ==================== ALBUM MAPPERS ====================
    
    public AlbumResponseDto toAlbumResponseDto(Album album) {
        return new AlbumResponseDto(
            album.getId(),
            album.getUser() != null ? album.getUser().getId() : null,
            album.getTitle(),
            album.getCreatedAt()
        );
    }
    
    public void updateAlbumFromDto(Album album, UpdateAlbumDto dto) {
        if (dto.getTitle() != null) {
            album.setTitle(dto.getTitle());
        }
    }
    
    // ==================== PHOTO MAPPERS ====================
    
    public PhotoResponseDto toPhotoResponseDto(Photo photo) {
        return new PhotoResponseDto(
            photo.getId(),
            photo.getAlbum() != null ? photo.getAlbum().getId() : null,
            photo.getTitle(),
            photo.getUrl(),
            photo.getThumbnailUrl(),
            photo.getCreatedAt()
        );
    }
    
    public void updatePhotoFromDto(Photo photo, UpdatePhotoDto dto) {
        if (dto.getTitle() != null) {
            photo.setTitle(dto.getTitle());
        }
        if (dto.getUrl() != null) {
            photo.setUrl(dto.getUrl());
        }
        if (dto.getThumbnailUrl() != null) {
            photo.setThumbnailUrl(dto.getThumbnailUrl());
        }
    }
}
