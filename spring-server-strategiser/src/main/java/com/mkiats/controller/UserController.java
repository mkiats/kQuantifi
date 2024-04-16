package com.mkiats.controller;

import com.mkiats.entity.User;
import com.mkiats.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		User theUser = userService.findUser(user_id);
		if (theUser==null) {
			throw new RuntimeException("User not found " + user_id);
		}
		return theUser;
	}
}
