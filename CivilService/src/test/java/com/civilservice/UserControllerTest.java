package com.civilservice;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.civilservice.controller.UserController;
import com.civilservice.entity.User;
import com.civilservice.service.ResolveDistanceService;
import com.fasterxml.jackson.core.JsonProcessingException;


@SpringBootTest
public class UserControllerTest {

	@Inject
	private UserController userController;
	@Inject
	private ResolveDistanceService resolveDistanceService;

	@Test
	public void getAllUsersTest() {
		List<User> users = userController.getAllUsers();
		assertThat(users.size()).isEqualTo(1000);
	}

	@Test
	public void getUserByIdTest() throws JsonProcessingException {
		User user1 = userController.getUserById(1);
		assertThat(user1.getFirst_name()).isEqualTo("Maurise");
		assertThat(user1.getEmail()).isEqualTo("mshieldon0@squidoo.com");

		User user1000 = userController.getUserById(1000);
		assertThat(user1000.getFirst_name()).isEqualTo("Ches");
		assertThat(user1000.getEmail()).isEqualTo("certeltrr@comsenz.com");
	}

	@Test
	public void testGetInstructions() {
		String instructions = userController.getInstructions();
		assertThat(instructions).asString().containsIgnoringCase("Build an API which calls this API");
	}
	
//	using manual testing initially, already know that users live in/around London : 
//	Ancell Garnsworthy, Hugo Lynd ,Phyllys Hebbs
	@Test
	public void getLondonersTest() {
		List<User> users = userController.getLondoners("london");
		
		assertThat(users.size()).isEqualTo(3);
		assertThat(users.get(0).getFirst_name()).isEqualTo("Ancell");
	}
	
	@Test
	public void resolveDistanceServiceTest() {
//	check resolveDistanceService separately => "Ancell Garnsworthy" location is within London
	List<User> users = userController.getLondoners("london");

	double latitude = users.get(0).getLatitude();
	double longitude = users.get(0).getLongitude();
	assertThat(resolveDistanceService.isLondon(latitude, longitude)).isTrue();
	}
	
	@Test
	public void isLondonTest() {
//		London's coordinates
		double latitude = 51.5074;
		double longitude = 0.1278;
		assertThat(resolveDistanceService.isLondon(latitude, longitude)).isTrue();
		
//		around London (less than 50 miles)
		double lat = 51.0000;
		double lon = 0.1000;
		assertThat(resolveDistanceService.isLondon(lat, lon)).isTrue();
	}
	
}
