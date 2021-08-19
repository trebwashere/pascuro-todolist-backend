package com.example.todolist;

import com.example.todolist.model.entity.Todo;
import com.example.todolist.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({MockitoExtension.class, SpringExtension.class})
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TodolistApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	TodoService todoService;

	@Test
	public void should_get_all_todos_when_getAllTodos_given_list_of_todos() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[*]",hasSize(4)));
	}

	@Test
	public void should_delete_todo_when_deleteTodo_given_id() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/todos/{id}", "1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.text").value("first to do item"));
	}

	@Test
	public void should_update_todo_done_status_when_updateToDo_given_id_and_done_status() throws Exception {
		String content = "{\"text\": \"update test\", \"done\": false}";
		mockMvc.perform(MockMvcRequestBuilders.put("/todos/{id}", "1")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value("1"))
				.andExpect(jsonPath("$.text").value("update test"))
				.andExpect(jsonPath("$.done").value(false));
	}

	@Test
	public void should_post_todo_when_addToDo_given_text_and_done_status() throws Exception {
		String content = "{\"text\": \"Hello world\", \"done\": false}";
		mockMvc.perform(MockMvcRequestBuilders.post("/todos/")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON)
						.content(content))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value("5"))
				.andExpect(jsonPath("$.text").value("Hello world"))
				.andExpect(jsonPath("$.done").value(false));
	}
}
