package com.telus.assesment.dto;

import com.telus.assesment.enums.Status;
import com.telus.assesment.model.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TodoDTOTest {
    @Test
    public void testBuilderAndAccessors() {
        String description = "im a todo";
        TodoDTO todo = TodoDTO.builder()
                .id(1)
                .status(Status.DONE)
                .description(description).build();
        assertEquals(1, todo.getId());
        assertEquals(description,todo.getDescription());
        assertEquals(Status.DONE,Status.DONE);
    }

    @Test
    public void testNoArgsConstructor() {
        TodoDTO todo = new TodoDTO();
        Assertions.assertInstanceOf(TodoDTO.class,todo);
    }

    @Test
    public void testToString() {

        String description = "im a todo";
        TodoDTO todo = TodoDTO.builder()
                .id(1)
                .status(Status.DONE)
                .description(description).build();

        String todoString = todo.toString();
        assertTrue(todoString.contains("TodoDTO"));
        assertTrue(todoString.contains("id=1"));
        assertTrue(todoString.contains("status=" + todo.getStatus().toString()));
        assertTrue(todoString.contains("description=" + todo.getDescription()));
    }

    @Test
    public void builderToStringTest() {

        String description = "im a todo";
        String builderToString = TodoDTO.builder()
                .id(1)
                .status(Status.DONE)
                .description(description).toString();

        assertNotNull(builderToString, "Builder toString() should not return null");
        assertTrue(builderToString.startsWith("TodoDTO.TodoDTOBuilder("),
                "Builder toString() should start with the class name");
    }
}
