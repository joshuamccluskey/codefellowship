package com.joshuamccluskey.codefellowship;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class CodefellowshipApplicationTests {
	@Autowired
	MockMvc mockMvc;

	@Test
	void contextLoads() {
		CodefellowshipApplication sut = new CodefellowshipApplication();
		assertNotNull(sut, "Yo check your app, it came up as null");
	}
	@Test
	void testIndexPage() throws Exception {
		mockMvc.perform(get("/"))
				.andExpect(content().string(containsString("Welcome")))
				.andExpect(status().isOk());
	}
	@Test
	void testLoginPage() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(content().string(containsString("Welcome")))
				.andExpect(status().isOk());
	}
	@Test
	void testSignUpPage() throws Exception {
		mockMvc.perform(get("/login"))
				.andExpect(content().string(containsString("Create")))
				.andExpect(status().isOk());
	}
}
