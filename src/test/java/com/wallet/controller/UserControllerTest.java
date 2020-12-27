package com.wallet.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wallet.dto.UserDTO;
import com.wallet.entity.User;
import com.wallet.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {
	
	private static final Long ID = 1L;
	private static final String EMAIL = "email@teste.com";
	private static final String NAME = "User Test";
	private static final String PASSWORD = "123456";
	private static final String URI = "/user";
	
	@MockBean
	UserService service;
	
	@Autowired
	MockMvc mvc;
	
	@Test
	public void testSave() throws Exception {
		
		BDDMockito.given(service.save(Mockito.any(User.class))).willReturn(getMockUser());
		
		mvc.perform(MockMvcRequestBuilders.post(URI).content(getJsonPayload(ID, EMAIL, NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.data.email").value(EMAIL))
		.andExpect(jsonPath("$.data.name").value(NAME))
		.andExpect(jsonPath("$.data.password").value(PASSWORD));
	}
	
	@Test
	public void testSaveInvalidUser() throws JsonProcessingException, Exception {
		
		mvc.perform(MockMvcRequestBuilders.post(URI).content(getJsonPayload(ID, "email", NAME, PASSWORD))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.errors[0]").value("E-mail invalido!!"));
		
	}
	
	public User getMockUser() {
		
		User u = new User();
		u.setEmail(EMAIL);
		u.setName(NAME);
		u.setPassword(PASSWORD);
		return u;
	}
	
	public String getJsonPayload(Long ID, String email, String name, String password) throws JsonProcessingException {
		
		UserDTO dto = new UserDTO();
		dto.setId(ID);
		dto.setEmail(email);
		dto.setName(name);
		dto.setPassword(password);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(dto);
	}
	
}
