package com.telus.assesment.testutils;

import com.telus.assesment.dto.TodoDTO;
import com.telus.assesment.enums.Status;
import com.telus.assesment.model.Todo;
import org.assertj.core.util.Lists;

import java.util.List;

public class TodoUtil {
    public static TodoDTO todoDTO1 = TodoDTO.builder()
            .id(1).description("Test Todo 1")
            .status(Status.PENDING)
            .build();
    public static TodoDTO todoDTO2 = TodoDTO.builder()
            .id(2).description("Test Todo 2")
            .status(Status.CREATED)
            .build();

    public static Todo todo1 = Todo.builder()
            .id(1L).description("Test Todo 1")
            .status(Status.PENDING)
            .build();
    public static Todo todo2 = Todo.builder()
            .id(2L).description("Test Todo 2")
            .status(Status.CREATED)
            .build();

    public static List<Todo> getTodosList(){
        return Lists.newArrayList(todo1,todo2);
    }

    public static Todo getSingleTodo(){
        return todo1;
    }

    public static TodoDTO getSingleTodoDTO(){
        return todoDTO1;
    }

    public static List<TodoDTO> getTodosDTOList(){
        return Lists.newArrayList(todoDTO1,todoDTO2);
    }
}
