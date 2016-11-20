package com.autosale.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.autosale.dao.CommentDao;
import com.autosale.model.Comment;

@RestController
//@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentDao commentDao;

	
	@RequestMapping("/getComment")
	public List<Comment> getComment(@RequestParam("carId") Integer carId){
		return commentDao.getCommentsByCarId(carId);
	}
	
	
	
	@RequestMapping("/addNewComment")
	public void addNewComment(@RequestParam("comment_text") String comment_text, 
			@RequestParam("carId") Integer carId){
    	commentDao.addNewComment(comment_text, carId);
	}
	

}
