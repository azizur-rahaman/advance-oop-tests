package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.dto.CreateUserDto;
import com.example.jsonplaceholder.dto.UpdateUserDto;
import com.example.jsonplaceholder.dto.UserResponseDto;
import com.example.jsonplaceholder.mapper.DtoMapper;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DtoMapper dtoMapper;
    
    // GET all users
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDto> userDtos = users.stream()
            .map(dtoMapper::toUserResponseDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    
    // GET user by ID
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Integer id) {
        return userService.getUserById(id)
            .map(user -> new ResponseEntity<>(dtoMapper.toUserResponseDto(user), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new user
    @PostMapping
    public ResponseEntity<UserResponseDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        User user = dtoMapper.toUser(createUserDto);
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(dtoMapper.toUserResponseDto(createdUser), HttpStatus.CREATED);
    }
    
    // PUT - Update entire user
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(@PathVariable Integer id, @Valid @RequestBody UpdateUserDto updateUserDto) {
        try {
            User user = dtoMapper.toUser(new CreateUserDto(
                updateUserDto.getName(),
                updateUserDto.getUsername(),
                updateUserDto.getEmail(),
                updateUserDto.getPhone(),
                updateUserDto.getWebsite()
            ));
            user.setId(id);
            User updatedUser = userService.updateUser(id, user);
            return new ResponseEntity<>(dtoMapper.toUserResponseDto(updatedUser), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update user
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> patchUser(@PathVariable Integer id, @RequestBody UpdateUserDto updateUserDto) {
        try {
            User existingUser = userService.getUserById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            dtoMapper.updateUserFromDto(existingUser, updateUserDto);
            User patchedUser = userService.patchUser(id, existingUser);
            return new ResponseEntity<>(dtoMapper.toUserResponseDto(patchedUser), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
