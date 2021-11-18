package com.anver.userservice.service;

import java.util.List;

import com.anver.userservice.model.User;

public interface UserService {

	User save(User user);

	User findByUsername(String username);

	List<String> findUsers(List<Long> idList);

}
