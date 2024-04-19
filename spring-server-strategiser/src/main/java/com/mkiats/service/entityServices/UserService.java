package com.mkiats.service.entityServices;


import com.mkiats.entity.User;
import org.springframework.data.domain.Page;
import java.util.List;

public interface UserService {
	public Page<User> getAllUsersByPage(int page, int size);
	public List<User> getAllUsers();
	public User getUser(String id);
}
