package com.personal.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.personal.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Serializable> {

	@Query("select u from User u where u.email=:email and u.password=:password")
	User loginUserRepo(@Param("email") String email, @Param("password") String password);
}
