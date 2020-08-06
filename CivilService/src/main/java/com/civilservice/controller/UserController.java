package com.civilservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.civilservice.entity.User;
import com.civilservice.service.ResolveDistanceService;

@RestController
public class UserController {
	
	private static final Logger LOG = LogManager.getLogger();
	
	@Inject
	private RestTemplate restTemplate;
	@Inject
	private ResolveDistanceService resolveDistanceService;
	
	@Value("${url:}")
	private String BaseURL;
	
	@GetMapping("/users")
	public List<User> getAllUsers() {
		ResponseEntity<List<User>> responseEntity = restTemplate.exchange(BaseURL + "/users", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<User>>() {
				});
		if(responseEntity.getStatusCode() != HttpStatus.OK) {
			LOG.error("API request was not successful");
		}
		return responseEntity.getBody();
	}
	
	@GetMapping("/user/{id}")
	public User getUserById(@PathVariable(required = true) long id) {
		ResponseEntity<User> responseEntity = restTemplate.exchange(BaseURL + "/user/" + id, HttpMethod.GET, null,
				new ParameterizedTypeReference<User>() {
				});
		if(responseEntity.getStatusCode() != HttpStatus.OK) {
			LOG.error("API request was not successful");
		}
		return responseEntity.getBody();
	}
	
	@GetMapping("/city/{city}/users")
	public List<User> getLondoners(@PathVariable(required = true) String city) {
		List<User> users = getAllUsers();
		List<User> londoners = new ArrayList<User>();
		if ("London".equalsIgnoreCase(city)) {
			londoners = users.stream().filter(user -> resolveDistanceService.isLondon(user.getLatitude(), user.getLongitude()) == true).collect(Collectors.toList());
		}
		return londoners;
	}
	
	@GetMapping("/instructions")
	public String getInstructions() {
		ResponseEntity<String> responseEntity = restTemplate.exchange(BaseURL + "/instructions", HttpMethod.GET, null,
				new ParameterizedTypeReference<String>() {
				});
		if(responseEntity.getStatusCode() != HttpStatus.OK) {
			LOG.error("API request was not successful");
		}
		return responseEntity.getBody();
	}
	
}
