package com.telus.assesment.service;

import com.telus.assesment.dto.TodoDTO;
import com.telus.assesment.model.Todo;
import com.telus.assesment.repository.TodoRepository;
import com.telus.assesment.testutils.TodoUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {
    @InjectMocks
    public TodoService todoService;
    @Mock
    public TodoRepository todoRepository;
    @Mock
    public ModelMapper modelMapper;

    @Test
    public void testGetAllTodos(){
        Mockito.when(todoRepository.findAll()).thenReturn(TodoUtil.getTodosList());
        todoService.getAll();
        Mockito.verify(todoRepository).findAll();
        Mockito.verify(modelMapper).map(TodoUtil.getTodosList().get(0), TodoDTO.class);
        Mockito.verify(modelMapper).map(TodoUtil.getTodosList().get(1), TodoDTO.class);
    }
    @Test
    public void getTodoById(){
        Mockito.when(todoRepository.findById(any())).thenReturn(Optional.of(TodoUtil.getSingleTodo()));
        todoService.getById(1);
        Mockito.verify(todoRepository).findById(1);
        Mockito.verify(modelMapper).map(TodoUtil.getSingleTodo(), TodoDTO.class);
    }

    @Test
    public void createTodo(){
        Mockito.when(todoRepository.save(any())).thenReturn(Optional.of(TodoUtil.getSingleTodo()).get());
        Mockito.when(modelMapper.map(TodoUtil.getSingleTodoDTO(),Todo.class)).thenReturn(Optional.of(TodoUtil.getSingleTodo()).get());
        todoService.createTodo(TodoUtil.getSingleTodoDTO());
        Mockito.verify(todoRepository).save(Optional.of(TodoUtil.getSingleTodo()).get());
        Mockito.verify(modelMapper).map(TodoUtil.getSingleTodo(), TodoDTO.class);

    }

    @Test
    public void updateTodo(){
        Mockito.when(todoRepository.findById(any())).thenReturn(Optional.of(TodoUtil.getSingleTodo()));
        Mockito.when(todoRepository.save(any())).thenReturn(Optional.of(TodoUtil.getSingleTodo()).get());
        todoService.updateTodo(Math.toIntExact(TodoUtil.getSingleTodo().getId()),TodoUtil.getSingleTodoDTO());
        Mockito.verify(todoRepository).save(Optional.of(TodoUtil.getSingleTodo()).get());
        Mockito.verify(modelMapper).map(TodoUtil.getSingleTodo(), TodoDTO.class);
    }

    @Test()
    public void updateTodoNotFound(){
        Mockito.when(todoRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        Exception ex = assertThrows(ResolutionException.class,()->  todoService.updateTodo(0,new TodoDTO()));
        assertEquals(ex.getMessage(),"TODO not found for update");
    }

    @Test
    public void deleteTodo(){
        Mockito.when(todoRepository.findById(any())).thenReturn(Optional.of(TodoUtil.getSingleTodo()));
        Mockito.doNothing().when(todoRepository).delete(TodoUtil.getSingleTodo());
        todoService.deleteTodo(0);
        Mockito.verify(todoRepository).findById(0);
        Mockito.verify(todoRepository).delete(TodoUtil.getSingleTodo());
        Mockito.verify(modelMapper).map(TodoUtil.getSingleTodo(), TodoDTO.class);
    }
    @Test
    public void deleteTodoNotFound(){
        //test
        Mockito.when(todoRepository.findById(any())).thenReturn(Optional.ofNullable(null));
        Exception ex = assertThrows(ResolutionException.class,()->  todoService.deleteTodo(0));
        assertEquals(ex.getMessage(),"TODO not found for deletion");
    }
}
