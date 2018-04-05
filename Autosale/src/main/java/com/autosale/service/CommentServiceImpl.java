package com.autosale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autosale.dao.CommentDao;
import com.autosale.model.Comment;
import com.autosale.service.interfaces.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> getCommentsById(int carId) {
		return commentDao.getCommentsByCarId(carId);
	}

	@Override
	public void addComment(String comment_text, int carId) {
		commentDao.addNewComment(comment_text, carId);

	}

}
