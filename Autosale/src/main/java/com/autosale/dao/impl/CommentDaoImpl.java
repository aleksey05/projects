package com.autosale.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.autosale.dao.CommentDao;
import com.autosale.model.Car;
import com.autosale.model.Comment;


@Repository
@Transactional
public class CommentDaoImpl implements CommentDao {

	@PersistenceContext
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Comment> getCommentsByCarId(int carId) {
		return em.createQuery("from Comment where carId= :carId")
				.setParameter("carId", carId).getResultList();
	}

	@Override
	public void addNewComment(String comment_text, int carId) {
		em.persist(new Comment(comment_text, em.find(Car.class, carId)));
	
	}

}
