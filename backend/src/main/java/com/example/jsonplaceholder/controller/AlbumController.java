package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.dto.CreateAlbumDto;
import com.example.jsonplaceholder.dto.UpdateAlbumDto;
import com.example.jsonplaceholder.dto.AlbumResponseDto;
import com.example.jsonplaceholder.mapper.DtoMapper;
import com.example.jsonplaceholder.model.Album;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.service.AlbumService;
import com.example.jsonplaceholder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/albums")
@CrossOrigin(origins = "*")
public class AlbumController {
    
    @Autowired
    private AlbumService albumService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DtoMapper dtoMapper;
    
    // GET all albums
    @GetMapping
    public ResponseEntity<List<AlbumResponseDto>> getAllAlbums(@RequestParam(required = false) Integer userId) {
        List<Album> albums;
        if (userId != null) {
            albums = albumService.getAlbumsByUserId(userId);
        } else {
            albums = albumService.getAllAlbums();
        }
        List<AlbumResponseDto> albumDtos = albums.stream()
            .map(dtoMapper::toAlbumResponseDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(albumDtos, HttpStatus.OK);
    }
    
    // GET album by ID
    @GetMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> getAlbumById(@PathVariable Integer id) {
        return albumService.getAlbumById(id)
            .map(album -> new ResponseEntity<>(dtoMapper.toAlbumResponseDto(album), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new album
    @PostMapping
    public ResponseEntity<AlbumResponseDto> createAlbum(@Valid @RequestBody CreateAlbumDto createAlbumDto) {
        try {
            User user = userService.getUserById(createAlbumDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            Album album = new Album();
            album.setUser(user);
            album.setTitle(createAlbumDto.getTitle());
            
            Album createdAlbum = albumService.createAlbum(album);
            return new ResponseEntity<>(dtoMapper.toAlbumResponseDto(createdAlbum), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire album
    @PutMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> updateAlbum(@PathVariable Integer id, @Valid @RequestBody UpdateAlbumDto updateAlbumDto) {
        try {
            Album existingAlbum = albumService.getAlbumById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));
            
            existingAlbum.setTitle(updateAlbumDto.getTitle());
            
            Album updatedAlbum = albumService.updateAlbum(id, existingAlbum);
            return new ResponseEntity<>(dtoMapper.toAlbumResponseDto(updatedAlbum), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update album
    @PatchMapping("/{id}")
    public ResponseEntity<AlbumResponseDto> patchAlbum(@PathVariable Integer id, @RequestBody UpdateAlbumDto updateAlbumDto) {
        try {
            Album existingAlbum = albumService.getAlbumById(id)
                .orElseThrow(() -> new RuntimeException("Album not found"));
            
            dtoMapper.updateAlbumFromDto(existingAlbum, updateAlbumDto);
            Album patchedAlbum = albumService.patchAlbum(id, existingAlbum);
            return new ResponseEntity<>(dtoMapper.toAlbumResponseDto(patchedAlbum), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE album
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        try {
            albumService.deleteAlbum(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
