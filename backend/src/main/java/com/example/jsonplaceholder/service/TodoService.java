package com.example.jsonplaceholder.service;

import com.example.jsonplaceholder.model.Todo;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.repository.TodoRepository;
import com.example.jsonplaceholder.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    
    @Autowired
    private TodoRepository todoRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // GET all todos
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
    
    // GET todo by ID
    public Optional<Todo> getTodoById(Integer id) {
        return todoRepository.findById(id);
    }
    
    // GET todos by user ID
    public List<Todo> getTodosByUserId(Integer userId) {
        return todoRepository.findByUserId(userId);
    }
    
    // POST - Create new todo
    public Todo createTodo(Todo todo) {
        if (todo.getUser() != null && todo.getUser().getId() != null) {
            User user = userRepository.findById(todo.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + todo.getUser().getId()));
            todo.setUser(user);
        }
        return todoRepository.save(todo);
    }
    
    // PUT - Update entire todo
    public Todo updateTodo(Integer id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        if (todoDetails.getUser() != null && todoDetails.getUser().getId() != null) {
            User user = userRepository.findById(todoDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + todoDetails.getUser().getId()));
            todo.setUser(user);
        }
        todo.setTitle(todoDetails.getTitle());
        todo.setCompleted(todoDetails.getCompleted());
        
        return todoRepository.save(todo);
    }
    
    // PATCH - Partially update todo
    public Todo patchTodo(Integer id, Todo todoDetails) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        
        if (todoDetails.getUser() != null && todoDetails.getUser().getId() != null) {
            User user = userRepository.findById(todoDetails.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + todoDetails.getUser().getId()));
            todo.setUser(user);
        }
        if (todoDetails.getTitle() != null) {
            todo.setTitle(todoDetails.getTitle());
        }
        if (todoDetails.getCompleted() != null) {
            todo.setCompleted(todoDetails.getCompleted());
        }
        
        return todoRepository.save(todo);
    }
    
    // DELETE todo
    public void deleteTodo(Integer id) {
        Todo todo = todoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo not found with id: " + id));
        todoRepository.delete(todo);
    }
}
