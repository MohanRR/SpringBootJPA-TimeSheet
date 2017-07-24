package com.personal.serviceimpl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

import org.omg.CORBA.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.dao.UserDAO;
import com.personal.entity.User;
import com.personal.repository.UserRepository;
import com.personal.response.Response;
import com.personal.service.UserService;
import com.personal.util.Constants;

@Service
public class UserServiceImpl implements UserService {
	private final Logger log =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private UserRepository userRepository;
	
	/* Using DAO */
	@Override
	public Response findByEmailAndPassword(String email, String password) {
		Response response = null;
		User user = userDAO.getUserByUsernameAndPassword(email, password);
			if(user!=null){
				response = new Response(user,"User already exist.",Constants.CUSTOM_ERROR);
			}else{
				response = new Response(user,"User not found.",Constants.SUCCESS);
			}
		return response;
	}
	
	@Override
	public Response loginUser(String email, String password) {
		Response response = null;
		String jwtToken = "";
		User user = userDAO.getUserByUsernameAndPassword(email, password);
			if(user!=null){
				jwtToken = Jwts.builder().setSubject(email).claim("password", password).setIssuedAt(new Date())
			            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
				user.setAuthToken(jwtToken);
				response = new Response(user,"Welcome "+user.getFirstName() +" "+ user.getLastName() ,Constants.SUCCESS);
			}else{
				response = new Response(user,"User was not exist",Constants.CUSTOM_ERROR);
			}
		return response;
	}

	@Override
	public Response saveOrUpdateUser(User user) {
		Response response = null;
		try{
			user.setCreatedBy(user.getFirstName()+" "+user.getLastName());
			user.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
			userDAO.saveOrUpdate(user);
			response = new Response(user,"New user created successfully",Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(user,e.getMessage(),Constants.ERROR);
			log.error("saveOrUpdateUser "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public Response findUserByUserId(String userId) {
		Response response = null;
		try{
		User user = userDAO.findByUserId(userId);
		if(user!=null){
			response = new Response(user, "New user created successfully", Constants.SUCCESS);
		}else{
			response = new Response(userId, "User was not found", Constants.CUSTOM_ERROR);
		}
		}catch(Exception e){
			response = new Response(userId,e.getMessage(),Constants.ERROR);
			log.error("findUserByUserId "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	public Response createNewUser(User user){
		Response response = null;
		try{
			if(validate(user)){
				response =  saveOrUpdateUser(user);
			}else{
				response = new Response(user,"User already registered",Constants.CUSTOM_ERROR);
			}
		}catch(Exception e){
			response = new Response(user,e.getMessage(),Constants.ERROR);
			log.error("createNewUser "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	public boolean validate(User user){
		boolean isUserAlreadyRegistered = false;
		Response response = findByEmailAndPassword(user.getEmail(), user.getPassword());
		if(response.getObject()!=null)
			isUserAlreadyRegistered = true;
		return isUserAlreadyRegistered;
	}
	
	public Response deleteUser(String userId){
		Response response = null;
		try{
			userDAO.deleteUser(userId);
			response = new Response(userId,"User Deleted Successfully",Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(userId,e.getMessage(),Constants.ERROR);
			log.error("deleteUser "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}

//===========================================================================================================================	
	/* Using Repository */
	
	@Override
	public Response findByEmailAndPasswordRepo(String email, String password) {
		Response response = null;
		User user = userRepository.loginUserRepo(email, password);
			if(user!=null){
				response = new Response(user,"User already exist.",Constants.CUSTOM_ERROR);
			}else{
				response = new Response(user,"User not found.",Constants.SUCCESS);
			}
		return response;
	}
	
	@Override
	public Response loginUserRepo(String email, String password) {
		Response response = null;
		String jwtToken = "";
		User user = userRepository.loginUserRepo(email, password);
			if(user!=null){
				jwtToken = Jwts.builder().setSubject(email).claim("password", password).setIssuedAt(new Date())
			            .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
				user.setAuthToken(jwtToken);
				response = new Response(user,"Welcome "+user.getFirstName() +" "+ user.getLastName() ,Constants.SUCCESS);
			}else{
				response = new Response(user,"User was not exist",Constants.CUSTOM_ERROR);
			}
		return response;
	}

	@Override
	public Response saveOrUpdateUserRepo(User user) {
		Response response = null;
		try{
			user.setCreatedBy(user.getFirstName()+" "+user.getLastName());
			user.setUpdatedBy(user.getFirstName()+" "+user.getLastName());
			userRepository.save(user);
			response = new Response(user,"New user created successfully",Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(user,e.getMessage(),Constants.ERROR);
			log.error("saveOrUpdateUser "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	@Override
	public Response findUserByUserIdRepo(String userId) {
		Response response = null;
		try{
		User user = userRepository.findOne(userId);
		if(user!=null){
			response = new Response(user, "New user created successfully", Constants.SUCCESS);
		}else{
			response = new Response(userId, "User was not found", Constants.CUSTOM_ERROR);
		}
		}catch(Exception e){
			response = new Response(userId,e.getMessage(),Constants.ERROR);
			log.error("findUserByUserId "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	public Response createNewUserRepo(User user){
		Response response = null;
		try{
			if(validateRepo(user)){
				response =  saveOrUpdateUserRepo(user);
			}else{
				response = new Response(user,"User already registered",Constants.CUSTOM_ERROR);
			}
		}catch(Exception e){
			response = new Response(user,e.getMessage(),Constants.ERROR);
			log.error("createNewUser "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
	
	public boolean validateRepo(User user){
		boolean isUserAlreadyRegistered = false;
		User  existUser = userRepository.loginUserRepo(user.getEmail(), user.getPassword());
		if(existUser!=null)
			isUserAlreadyRegistered = true;
		return isUserAlreadyRegistered;
	}
	
	public Response deleteUserRepo(String userId){
		Response response = null;
		try{
			userRepository.delete(userId);
			response = new Response(userId,"User Deleted Successfully",Constants.SUCCESS);
		}catch(Exception e){
			response = new Response(userId,e.getMessage(),Constants.ERROR);
			log.error("deleteUser "+e.getMessage());
			e.printStackTrace();
		}
		return response;
	}
}
