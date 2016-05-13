package com.kickstarter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kickstarter.dao.Interfaces.PaymentDao;
import com.kickstarter.dao.Interfaces.ProjectDao;
import com.kickstarter.dao.Interfaces.QuestionDao;
import com.kickstarter.model.Project;
import com.kickstarter.model.Question;

@Controller
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	QuestionDao questionDao;

	@Autowired
	ProjectDao projectDao;

	@Autowired
	PaymentDao paymentDao;

	@RequestMapping("/list")
	public ModelAndView getAllProjects(@RequestParam(name = "categoryId") int categoryId) {
		ModelAndView modelAndView = new ModelAndView("categoryProjects");
		List<Project> projectList = projectDao.getAllProjectsForCategory(categoryId);
		for (Project p : projectList) {
			p.setGainedSum(paymentDao.getAll(p.getId()));
		}
		modelAndView.addObject("projectList", projectList); 
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getOneProject(@RequestParam(name = "projectId") int projectId) {
		ModelAndView modelAndView = new ModelAndView("project");

		Project project = projectDao.getOneProject(projectId);
		project.setGainedSum(paymentDao.getAll(projectId));
		List<Question> list = questionDao.getProjectQuestions(projectId);// FIXME

		modelAndView.addObject("project", project);
		modelAndView.addObject("questions", list);

		return modelAndView;
	}

}
