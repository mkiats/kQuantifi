package com.mkiats.services.entityServices;


import com.mkiats.entities.User;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserService {
	public Page<User> getAllUsersByPage(int page, int size);
	public List<User> getAllUsers();
	public User getUser(String id);
}
