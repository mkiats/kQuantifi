package com.mkiats.controllers;

import com.mkiats.dataAccessObjects.UserServiceDao;
import com.mkiats.entities.User;
import java.util.List;

import com.mkiats.temp.TempClass;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class UserController {

	private final UserServiceDao userService;

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@GetMapping("/users/{user_id}")
	public User getUser(@PathVariable String user_id) {
		System.out.println(user_id);
		User theUser = userService.getUser(	user_id);
		if (theUser == null) {
			throw new RuntimeException("User not found " + user_id);
		}
		return theUser;
	}

	@PostMapping("/tickers")
	public String getTicker(){
		System.out.println("Request Received...");
		TempClass json = new TempClass();
		String jsonStr = json.getJsonStringShort();
		return jsonStr;
	}
}
