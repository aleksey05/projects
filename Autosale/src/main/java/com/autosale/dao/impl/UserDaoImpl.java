package com.autosale.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.autosale.dao.UserDao;
import com.autosale.model.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	public void addUser(User user) {
		em.persist(user);
	}

	public void editUser(User user) {
		em.merge(user);
	}

	public void deleteUser(int userId) {
		em.remove(getUser(userId));
	}

	public User getUser(int userId) {
		return em.find(User.class, userId);
	}

	public User getUserByName(String userName) {
		return (User) em.createQuery("from User where name= :userName").setParameter("userName", userName)
				.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<User> getUserList() {
		return em.createQuery("from User").getResultList();
	}

}
