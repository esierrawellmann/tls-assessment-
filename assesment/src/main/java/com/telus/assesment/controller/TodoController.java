package com.telus.assesment.controller;

import com.telus.assesment.dto.TodoDTO;
import com.telus.assesment.service.TodoService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAll() {
        return ResponseEntity.ok().body(todoService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable("id") int id) {
        return ResponseEntity.ok().body(todoService.getById(Integer.valueOf(id)));
    }
    @PostMapping
    public ResponseEntity<TodoDTO> createTodo(@Validated @RequestBody TodoDTO todo, HttpServletRequest request) {
        if(StringUtils.isEmpty(todo.getDescription()) || todo.getStatus() == null ||  StringUtils.isEmpty(todo.getStatus().toString()))
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().body(todoService.createTodo(todo));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("id") int id , @Validated @RequestBody TodoDTO todo, HttpServletRequest request) {

        return ResponseEntity.ok().body(todoService.updateTodo(id,todo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDTO> deleteTodo(@PathVariable("id") int todoId) {
        return ResponseEntity.ok().body(todoService.deleteTodo(todoId));
    }

}
