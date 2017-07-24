package com.personal.dao;

import com.personal.entity.User;

public interface UserDAO {

	public User getUserByUsernameAndPassword(String userName, String password);
	public void saveOrUpdate(User user);
	public User findByUserId(String userId);
	public void deleteUser(String userId);
}
