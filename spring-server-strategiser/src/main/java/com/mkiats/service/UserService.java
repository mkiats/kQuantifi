package com.mkiats.service;

import com.mkiats.dao.UserRepo;
import com.mkiats.entity.User;
import jakarta.transaction.Transactional;
import javax.management.RuntimeErrorException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
public class UserService {

	private final UserRepo userRepo;

	public Page<User> findAllUsersByPage(int page, int size) {
		return userRepo.findAll(
			PageRequest.of(page, size, Sort.by("username"))
		);
	}

	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

	public User findUser(String id) {
		return userRepo
			.findById(id)
			.orElseThrow(
				() -> new RuntimeErrorException(new Error("User not found"))
			);
	}
}
