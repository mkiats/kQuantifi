package com.mkiats.controller;

import com.mkiats.entity.User;
import com.mkiats.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class UserController {

	private final UserService userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.findAllUsers();
	}

	@GetMapping("/users/{user_id}")
	public User getUser(@PathVariable String user_id) {
		System.out.println(user_id);
		User theUser = userService.findUser(user_id);
		if (theUser == null) {
			throw new RuntimeException("User not found " + user_id);
		}
		return theUser;
	}
}
