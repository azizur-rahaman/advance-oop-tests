package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.Album;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.repository.AlbumRepository;
import com.example.jsonplaceholder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlbumService {
    
    @Autowired
    private AlbumRepository albumRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // GET all albums
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }
    
    // GET album by ID
    public Optional<Album> getAlbumById(Integer id) {
        return albumRepository.findById(id);
    }
    
    // GET albums by user ID
    public List<Album> getAlbumsByUserId(Integer userId) {
        return albumRepository.findByUserId(userId);
    }
    
    // POST - Create new album
    public Album createAlbum(Album album) {
        if (album.getUser() != null && album.getUser().getId() != null) {
            User user = userRepository.findById(album.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + album.getUser().getId()));
            album.setUser(user);
        }
        return albumRepository.save(album);
    }
    
    // PUT - Update entire album
    public Album updateAlbum(Integer id, Album albumDetails) {
        Album album = albumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Album not found with id: " + id));
        
        if (albumDetails.getUser() != null && albumDetails.getUser().getId() != null) {
            User user = userRepository.findById(albumDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + albumDetails.getUser().getId()));
            album.setUser(user);
        }
        album.setTitle(albumDetails.getTitle());
        
        return albumRepository.save(album);
    }
    
    // PATCH - Partially update album
    public Album patchAlbum(Integer id, Album albumDetails) {
        Album album = albumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Album not found with id: " + id));
        
        if (albumDetails.getUser() != null && albumDetails.getUser().getId() != null) {
            User user = userRepository.findById(albumDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + albumDetails.getUser().getId()));
            album.setUser(user);
        }
        if (albumDetails.getTitle() != null) {
            album.setTitle(albumDetails.getTitle());
        }
        
        return albumRepository.save(album);
    }
    
    // DELETE album
    public void deleteAlbum(Integer id) {
        Album album = albumRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Album not found with id: " + id));
        albumRepository.delete(album);
    }
}
