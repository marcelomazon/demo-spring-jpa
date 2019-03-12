package com.mazon.demo;

import com.mazon.demo.user.User;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoSpringJpaApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	@Test
	public void contextLoads() {
	}
	@Test
	public void testGetAllUsers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/users",
				HttpMethod.GET, entity, String.class);
		Assert.assertNotNull(response.getBody());
	}
	@Test
	public void testGetUserById() {
		User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
		System.out.println(user.getNome());
		Assert.assertNotNull(user);
	}
	@Test
	public void testCreateUser() {
		User user = new User();
		user.setEmail("admin@gmail.com");
		user.setNome("admin");
		user.setDhCriacao(LocalDateTime.now());
		user.setDhAtualizacao(LocalDateTime.now());
		ResponseEntity<User> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", user, User.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}
	@Test
	public void testUpdatePost() {
		int id = 1;
		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		user.setNome("admin1");
		restTemplate.put(getRootUrl() + "/users/" + id, user);
		User updatedUser = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		Assert.assertNotNull(updatedUser);
	}
	@Test
	public void testDeletePost() {
		int id = 2;
		User user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		Assert.assertNotNull(user);
		restTemplate.delete(getRootUrl() + "/users/" + id);
		try {
			user = restTemplate.getForObject(getRootUrl() + "/users/" + id, User.class);
		} catch (final HttpClientErrorException e) {
			Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
		}
	}
}