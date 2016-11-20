package com.autosale.dao;

import java.util.List;

import com.autosale.model.Comment;

public interface CommentDao {
	
	List<Comment> getCommentsByCarId(int carId);
	
	 void addNewComment(String comment_text, int carId);

}
