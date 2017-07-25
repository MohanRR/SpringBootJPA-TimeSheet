package com.personal.daoimpl;

import java.util.List;

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
		User user = null;
		List<User> users =  entityManager.createNamedQuery("findUserByUsernameAndPassword", User.class).setParameter("email", email).setParameter("password", password).getResultList();
		if(users!=null && users.size()>0)
			user = users.get(0);
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
