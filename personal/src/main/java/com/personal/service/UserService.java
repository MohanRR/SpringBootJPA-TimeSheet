package com.personal.service;

import com.personal.entity.User;
import com.personal.response.Response;

public interface UserService {

	/* Using DAO */
	public Response createNewUser(User user);
	public Response loginUser(User user);
	public Response findByEmailAndPassword(String email, String password);
	public Response saveOrUpdateUser(User user);
	public Response findUserByUserId(String userId);
	public Response deleteUser(String userId);
	
	//--------------------------------------------------------------------------------------
	/* Using Repository */
	public Response createNewUserRepo(User user);
	public Response loginUserRepo(String email, String password);
	public Response findByEmailAndPasswordRepo(String email, String password);
	public Response saveOrUpdateUserRepo(User user);
	public Response findUserByUserIdRepo(String userId);
	public Response deleteUserRepo(String userId);
}
