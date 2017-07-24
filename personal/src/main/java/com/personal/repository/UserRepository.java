package com.personal.repository;

import java.io.Serializable;

import javax.ws.rs.QueryParam;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.personal.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Serializable> {

	@Query("select u from User u where u.email=:email and password=:password")
	User loginUserRepo(@QueryParam("email") String email, @QueryParam("password") String password);
}
