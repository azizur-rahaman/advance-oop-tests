package com.example.jsonplaceholder.controller;

import com.example.jsonplaceholder.dto.CreateTodoDto;
import com.example.jsonplaceholder.dto.UpdateTodoDto;
import com.example.jsonplaceholder.dto.TodoResponseDto;
import com.example.jsonplaceholder.mapper.DtoMapper;
import com.example.jsonplaceholder.model.Todo;
import com.example.jsonplaceholder.model.User;
import com.example.jsonplaceholder.service.TodoService;
import com.example.jsonplaceholder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*")
public class TodoController {
    
    @Autowired
    private TodoService todoService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private DtoMapper dtoMapper;
    
    // GET all todos
    @GetMapping
    public ResponseEntity<List<TodoResponseDto>> getAllTodos(@RequestParam(required = false) Integer userId) {
        List<Todo> todos;
        if (userId != null) {
            todos = todoService.getTodosByUserId(userId);
        } else {
            todos = todoService.getAllTodos();
        }
        List<TodoResponseDto> todoDtos = todos.stream()
            .map(dtoMapper::toTodoResponseDto)
            .collect(Collectors.toList());
        return new ResponseEntity<>(todoDtos, HttpStatus.OK);
    }
    
    // GET todo by ID
    @GetMapping("/{id}")
    public ResponseEntity<TodoResponseDto> getTodoById(@PathVariable Integer id) {
        return todoService.getTodoById(id)
            .map(todo -> new ResponseEntity<>(dtoMapper.toTodoResponseDto(todo), HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // POST - Create new todo
    @PostMapping
    public ResponseEntity<TodoResponseDto> createTodo(@Valid @RequestBody CreateTodoDto createTodoDto) {
        try {
            User user = userService.getUserById(createTodoDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            Todo todo = new Todo();
            todo.setUser(user);
            todo.setTitle(createTodoDto.getTitle());
            todo.setCompleted(createTodoDto.getCompleted());
            
            Todo createdTodo = todoService.createTodo(todo);
            return new ResponseEntity<>(dtoMapper.toTodoResponseDto(createdTodo), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    // PUT - Update entire todo
    @PutMapping("/{id}")
    public ResponseEntity<TodoResponseDto> updateTodo(@PathVariable Integer id, @Valid @RequestBody UpdateTodoDto updateTodoDto) {
        try {
            Todo existingTodo = todoService.getTodoById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
            
            existingTodo.setTitle(updateTodoDto.getTitle());
            existingTodo.setCompleted(updateTodoDto.getCompleted());
            
            Todo updatedTodo = todoService.updateTodo(id, existingTodo);
            return new ResponseEntity<>(dtoMapper.toTodoResponseDto(updatedTodo), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    // PATCH - Partially update todo
    @PatchMapping("/{id}")
    public ResponseEntity<TodoResponseDto> patchTodo(@PathVariable Integer id, @RequestBody UpdateTodoDto updateTodoDto) {
        try {
            Todo existingTodo = todoService.getTodoById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
            
            dtoMapper.updateTodoFromDto(existingTodo, updateTodoDto);
            Todo patchedTodo = todoService.patchTodo(id, existingTodo);
            return new ResponseEntity<>(dtoMapper.toTodoResponseDto(patchedTodo), HttpStatus.OK);
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
