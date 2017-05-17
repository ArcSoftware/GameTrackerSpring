package com.theironyard.charlotte;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.theironyard.charlotte.entities.User;
import com.theironyard.charlotte.services.UserRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GameTrackerSpringApplicationTests {
	private MockMvc mockMvc;
	@Autowired
	UserRepo users;

	@Autowired
	WebApplicationContext wac;

	@Before
	public void before() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void addUser() throws Exception {
		User user = new User();
		user.setName("Test");
		user.setPassword("1234");
		ObjectMapper mapper = new ObjectMapper();

		String json = mapper.writeValueAsString(user);

		int originalCount = (int)users.count();


		mockMvc.perform(
				MockMvcRequestBuilders.post("/add-user")
						.content(json)
						.contentType("application/json")
		);

		Assert.assertTrue(users.count() == originalCount + 1);
	}




}

