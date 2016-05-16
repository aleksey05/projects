package com.autosale.controller;

import com.autosale.dao.CommentDao;
import com.autosale.model.Car;
import com.autosale.service.interfaces.CarService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CarController {

	@Autowired
	CarService carService;

	@Autowired
	CommentDao commentDao;

	@RequestMapping("/add")
	public ModelAndView add() {
		return new ModelAndView("addForm");

	}

	@RequestMapping("/addNew")
	public String addNewCar(@RequestParam Map<String, String> requestParams,
			@RequestParam("image") MultipartFile image) {
		carService.addNewCar(requestParams, image);
		return "main";
	}

	@RequestMapping("/cars")
	public ModelAndView getCarsByParams(@RequestParam Map<String, String> requestParams) {
		List<Car> carList = carService.getCarsList(requestParams);
		return new ModelAndView("list", "carList", carList);

	}

	@RequestMapping("/car")
	public ModelAndView getCars(@RequestParam("carId") String carId) {
		ModelAndView modelAndView = new ModelAndView("car");
		modelAndView.addObject("car", carService.getCarById(carId));
		modelAndView.addObject("comments", commentDao.getCommentsByCarId(Integer.valueOf(carId)));
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView returnMainPage() {
		return new ModelAndView("main");

	}

}
