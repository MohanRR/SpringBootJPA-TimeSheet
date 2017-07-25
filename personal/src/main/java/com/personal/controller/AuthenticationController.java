package com.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.entity.User;
import com.personal.response.Response;
import com.personal.service.UserService;

@Controller
@RequestMapping("auth")
public class AuthenticationController {

	@Autowired
	UserService userService;
	
	
	@PostMapping("/register")
	public ResponseEntity<Response> registerNewUser(@RequestBody User user){
		Response response = userService.createNewUser(user);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody User user){
		//Response response = userService.loginUser(user);
		Response response = userService.loginUserRepo(user.getEmail(), user.getPassword());
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
