package com.autosale.service.interfaces;

import java.util.List;

import com.autosale.model.Comment;

public interface CommentService {

	List<Comment> getCommentsById(int carId);

	void addComment(String comment_text, int carId);

}
