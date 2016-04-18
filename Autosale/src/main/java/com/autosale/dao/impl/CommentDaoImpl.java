package com.autosale.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.autosale.dao.CommentDao;
import com.autosale.model.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Comment> getCommentsByCarId(int carId) {
		return em.createQuery("from Comment where carId= :carId")
				.setParameter("carId", carId).getResultList();
	}

}
