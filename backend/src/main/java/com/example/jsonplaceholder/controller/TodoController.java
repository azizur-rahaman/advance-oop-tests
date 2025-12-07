package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.model.Todo;
import com.example.jsonplaceholder.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    // GET all todos
    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam(required = false) Integer userId) {
        List<Todo> todos;
        if (userId != null) {
            todos = todoService.getTodosByUserId(userId);
        } else {
            todos = todoService.getAllTodos();
        }
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    
    // GET todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Integer id) {
        return todoService.getTodoById(id)
            .map(todo -> new ResponseEntity<>(todo, HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new todo
    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        try {
            Todo createdTodo = todoService.createTodo(todo);
            return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire todo
    @PutMapping("/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        try {
            Todo updatedTodo = todoService.updateTodo(id, todo);
            return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update todo
    @PatchMapping("/{id}")
    public ResponseEntity<Todo> patchTodo(@PathVariable Integer id, @RequestBody Todo todo) {
        try {
            Todo patchedTodo = todoService.patchTodo(id, todo);
            return new ResponseEntity<>(patchedTodo, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // DELETE todo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Integer id) {
        try {
            todoService.deleteTodo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
