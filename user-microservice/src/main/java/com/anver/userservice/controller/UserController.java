package com.anver.userservice.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.anver.userservice.model.Role;
import com.anver.userservice.model.User;
import com.anver.userservice.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/service/registration")
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		if(userService.findByUsername(user.getUsername()) != null) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		user.setRole(Role.USER);
		return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED); 
	}
	
	@GetMapping("/service/login")
	public ResponseEntity<?> getUser(Principal principal) {
		if(principal == null || principal.getName() == null) {
			// logout
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return ResponseEntity.ok(userService.findByUsername(principal.getName()));	
	}
	
	@PostMapping("/service/names")
	public ResponseEntity<?> getNamesOfUsers(@RequestBody List<Long> idList) {
		return ResponseEntity.ok(userService.findUsers(idList));
	}
	
	@GetMapping("/service/test")
	public ResponseEntity<?> test() {
		return ResponseEntity.ok("All its ok");
	}
} 
