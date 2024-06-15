package com.telus.assesment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.telus.assesment.ApplicationConfig;
import com.telus.assesment.dto.TodoDTO;
import com.telus.assesment.enums.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(ApplicationConfig.class)
public class TodoControllerTest {

    private static final String TODO_PAHT = "/todos";
    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldGetAllTheTodosWith200AsResponseStatus() throws Exception {

        this.mockMvc.perform(MockMvcRequestBuilders.get(TODO_PAHT).contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void shouldCreateTodosWith200AsResponseStatus() throws Exception {

        String description = "Im a new Todo";

        TodoDTO todoDTO = TodoDTO.builder().description(description).status(Status.CREATED).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todoDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.post(TODO_PAHT).content(todoJson).contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$['description']").value(description));
    }

    @Test
    void shouldCreateAndGetATodoWith200AsResponseStatus() throws Exception {

        String description = "Im a new Todo";

        TodoDTO todoDTO = TodoDTO.builder().description(description).status(Status.CREATED).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todoDTO);
        MvcResult postResult =  this.mockMvc.perform(MockMvcRequestBuilders.post(TODO_PAHT).content(todoJson).contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$['description']").value(description)).andReturn();

        TodoDTO newTodo = objectMapper.readValue(postResult.getResponse().getContentAsString(),TodoDTO.class);


        this.mockMvc.perform(MockMvcRequestBuilders.get(TODO_PAHT +"/"+ newTodo.getId()).content(todoJson).contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$['id']").value(newTodo.getId()))
                .andExpect(jsonPath("$['description']").value(description));
    }

    @Test
    void shouldFailTodosWithMissingStatusField400AsResponseStatus() throws Exception {

        String description = "Im a new Todo";

        TodoDTO todoDTO = TodoDTO.builder().description(description).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todoDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.post(TODO_PAHT).content(todoJson).contentType("application/json"))
                .andExpect(status().is(400));
    }

    @Test
    void shouldCreateAndModifyTodosWith200AsResponseStatus() throws Exception {

        String description = "Im a new Todo";
        String secondDescription = "I told you i can chance";
        TodoDTO todoDTO = TodoDTO.builder().description(description).status(Status.CREATED).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todoDTO);
        MvcResult postResult =  this.mockMvc.perform(MockMvcRequestBuilders.post(TODO_PAHT).content(todoJson).contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$['description']").value(description)).andReturn();

        TodoDTO newTodo = objectMapper.readValue(postResult.getResponse().getContentAsString(),TodoDTO.class);
        newTodo.setDescription(secondDescription);
        newTodo.setStatus(Status.PENDING);

        String modifiedJson = objectMapper.writeValueAsString(newTodo);
        this.mockMvc.perform(MockMvcRequestBuilders.patch(TODO_PAHT + "/" + newTodo.getId())
                .content(modifiedJson)
                .contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$['description']").value(secondDescription))
                .andExpect(jsonPath("$['status']").value(Status.PENDING.toString()));
    }

    @Test
    void shouldDeleteTodosWith200AsResponseStatus() throws Exception {

        String description = "Im a new Todo";

        TodoDTO todoDTO = TodoDTO.builder().description(description).status(Status.CREATED).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String todoJson = objectMapper.writeValueAsString(todoDTO);
        MvcResult postResult =  this.mockMvc.perform(MockMvcRequestBuilders.post(TODO_PAHT).content(todoJson).contentType("application/json"))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$['description']").value(description)).andReturn();
        TodoDTO newTodo = objectMapper.readValue(postResult.getResponse().getContentAsString(),TodoDTO.class);
        this.mockMvc.perform(MockMvcRequestBuilders.delete(TODO_PAHT +"/"+ newTodo.getId()).contentType("application/json"))
                .andExpect(status().is(200));


    }
}
