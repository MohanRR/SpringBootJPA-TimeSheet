package com.personal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.personal.entity.Detail;
import com.personal.entity.TimeSheet;
import com.personal.entity.User;
import com.personal.response.Response;
import com.personal.service.UserService;
import com.personal.util.Constants;

@Controller
@RequestMapping("user")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<Response> findUserById(@PathVariable("id") String id){
		Response response = null;
		if(id==null){
			response = userService.findUserByUserId(id);
		}else{
			response = new Response(id, "User was not found.", Constants.CUSTOM_ERROR);
		}
		return  new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@PostMapping("find-user")
	public ResponseEntity<Response> findUserByUsernameAndPassword(@RequestBody User user){
		Response response = null;
		if(user!=null){
			response = userService.findByEmailAndPassword(user.getEmail(), user.getPassword());
		}else{
			response = new Response(user, "User was not found.", Constants.CUSTOM_ERROR);
		}
		return  new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	

	@DeleteMapping("{id}")
	public ResponseEntity<Response> updateUser(@PathVariable("id") String userId){
		Response response = null;
		if(userId!=null && !userId.trim().isEmpty()){
			response = userService.deleteUser(userId);
		}else{
			response = new Response(userId, "User was not found.", Constants.CUSTOM_ERROR);
		}
		return  new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
