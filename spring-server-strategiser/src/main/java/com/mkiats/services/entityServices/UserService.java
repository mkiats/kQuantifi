package com.mkiats.services.entityServices;

import com.mkiats.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserService {
	public Page<User> getAllUsersByPage(int page, int size);

	public List<User> getAllUsers();

	public User getUser(String id);
}
