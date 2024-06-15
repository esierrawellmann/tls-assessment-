package com.telus.assesment.service;

import com.telus.assesment.dto.TodoDTO;
import com.telus.assesment.model.Todo;
import com.telus.assesment.repository.TodoRepository;
import io.micrometer.common.util.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    TodoRepository todoRepository;
    public List<TodoDTO> getAll(){
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> modelMapper.map(todo,TodoDTO.class))
                .collect(Collectors.toList());
    }

    public TodoDTO getById(Integer id){
        Optional<Todo> todos = todoRepository.findById(id);
        return modelMapper.map(todos.orElse(null),TodoDTO.class);
    }

    public TodoDTO createTodo(TodoDTO dto){
        Todo newTodo  = modelMapper.map(dto,Todo.class);
        Todo savedTodo = todoRepository.save(newTodo);
        return  modelMapper.map(savedTodo,TodoDTO.class);
    }

    public TodoDTO updateTodo(int id,TodoDTO dto){
        Todo updated  = todoRepository.findById(id).orElseThrow(() -> new ResolutionException("TODO not found for update"));
        updated.setDescription(dto.getDescription());
        updated.setStatus(dto.getStatus());
        Todo savedTodo = todoRepository.save(updated);
        return  modelMapper.map(savedTodo,TodoDTO.class);
    }

    public TodoDTO deleteTodo(int id){
        Todo deletedTodo  = todoRepository.findById(id).orElseThrow(() -> new ResolutionException("TODO not found for deletion"));
        todoRepository.delete(deletedTodo);
        return  modelMapper.map(deletedTodo,TodoDTO.class);
    }
}
