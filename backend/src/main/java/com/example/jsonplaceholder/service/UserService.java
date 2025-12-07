package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    // GET all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    // GET user by ID
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id);
    }
    
    // POST - Create new user
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    // PUT - Update entire user
    public User updateUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        user.setName(userDetails.getName());
        user.setUsername(userDetails.getUsername());
        user.setEmail(userDetails.getEmail());
        user.setPhone(userDetails.getPhone());
        user.setWebsite(userDetails.getWebsite());
        
        return userRepository.save(user);
    }
    
    // PATCH - Partially update user
    public User patchUser(Integer id, User userDetails) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        
        if (userDetails.getName() != null) {
            user.setName(userDetails.getName());
        }
        if (userDetails.getUsername() != null) {
            user.setUsername(userDetails.getUsername());
        }
        if (userDetails.getEmail() != null) {
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPhone() != null) {
            user.setPhone(userDetails.getPhone());
        }
        if (userDetails.getWebsite() != null) {
            user.setWebsite(userDetails.getWebsite());
        }
        
        return userRepository.save(user);
    }
    
    // DELETE user
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.delete(user);
    }
}
