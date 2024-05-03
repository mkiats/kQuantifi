package com.mkiats.dataAccessObjects;

import com.mkiats.entities.User;
import java.util.List;
import org.springframework.data.domain.Page;

public interface UserServiceDao {
	public Page<User> getAllUsersByPage(int page, int size);

	public List<User> getAllUsers();

	public User getUser(String id);
}
