package com.anver.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anver.userservice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	public Optional<User> findByUsername(String username);
	
	@Query("select u.username from User u where u.id in (:pIdList)")
	public List<String> findByIdList(@Param("pIdList") List<Long> idList);
	
}
