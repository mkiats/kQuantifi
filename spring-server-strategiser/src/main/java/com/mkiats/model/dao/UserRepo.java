package com.mkiats.model.dao;

import com.mkiats.model.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	Optional<User> findById(String id);
}
