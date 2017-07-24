package com.personal.daoimpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.personal.dao.UserDAO;
import com.personal.entity.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public User getUserByUsernameAndPassword(String email, String password) {
		User user = (User) entityManager.createNamedQuery("findUserByUsernameAndPassword").setParameter("email", email).setParameter("password", password).getSingleResult();
		return user;
	}

	@Override
	public void saveOrUpdate(User user) {
		entityManager.persist(user);
	}

	@Override
	public User findByUserId(String userId) {
		return entityManager.find(User.class, userId);
	}

	public void deleteUser(String userId){
		entityManager.remove(findByUserId(userId));
	}

}
