package com.autosale.controller;

import com.autosale.dao.CarDao;
import com.autosale.dao.CommentDao;
import com.autosale.dao.UserDao;
import com.autosale.model.Car;
import com.autosale.model.Comment;
import com.autosale.model.FuelType;
import com.autosale.model.GearType;
import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	CarDao carDao;

	@Autowired
	UserDao userDao;

	@Autowired
	CommentDao commentDao;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView doSomething() {
		return new ModelAndView("main");

	}

	@RequestMapping("/add")
	public ModelAndView add() {
		return new ModelAndView("addForm");

	}

	@RequestMapping("/addNew")
	public String addNewCar(@RequestParam("brand") String brand, @RequestParam("model") String model,
			@RequestParam("year") String year, @RequestParam("image") MultipartFile image,
			@RequestParam("gearType") String gearType, @RequestParam("fuelType") String fuelType,
			@RequestParam("engineVolume") String engineVolume, @RequestParam("colour") String colour,
			@RequestParam("additionalInfo") String additionalInfo, @RequestParam("mileage") String mileage) {

		if (!image.isEmpty()) {// TODO extract data partially to Car Service
			try {
				byte[] bytes = image.getBytes();
				// Creating the directory to store file
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "tmpFiles");
				if (!dir.exists())
					dir.mkdirs();
				// Create the file on server
				File serverFile = new File(dir.getAbsolutePath() + File.separator + image.getOriginalFilename());
				FileUtils.writeByteArrayToFile(serverFile, bytes);
				String imageUrl = serverFile.getAbsolutePath();

				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				String name = auth.getName();

				Car car = new Car();

				car.setAdditionalInfo(additionalInfo);
				car.setBrand(brand);
				car.setColour(colour);
				car.setEngineVolume(engineVolume);
				car.setFuel(FuelType.valueOf(fuelType.toUpperCase(Locale.ENGLISH)));
				car.setGear(GearType.valueOf(gearType.toUpperCase(Locale.ENGLISH)));
				car.setImage_url(imageUrl);
				car.setModel(model);
				car.setYear(year);
				car.setDate(new Timestamp(System.currentTimeMillis()));
				car.setMileage(mileage);
				car.setUser(userDao.getUserByName(name));// It would be much
															// better to get
															// user byId not
															// name or to check
															// names to be
															// unique in
															// dataBase

				carDao.addNewCar(car);
				return "main";

			} catch (Exception e) {
				return "You failed to upload" + e.getMessage();
			}
		} else {
			return "main";
		}
	}

	@RequestMapping("/cars")
	public ModelAndView getCarsByParams(@RequestParam Map<String, String> requestParams) {
		List<Car> carList = carDao.getCarsByCriteria(requestParams);
		return new ModelAndView("list", "carList", carList);

	}

	@RequestMapping("/car")
	public ModelAndView getCars(@RequestParam("carId") String carId) {
		Car car = carDao.getCarById(Integer.valueOf(carId));
		List<Comment> comments = commentDao.getCommentsByCarId(Integer.valueOf(carId));
		ModelAndView modelAndView = new ModelAndView("car");
		modelAndView.addObject("car", car);
		modelAndView.addObject("comments", comments);
		return modelAndView;
	}
}
