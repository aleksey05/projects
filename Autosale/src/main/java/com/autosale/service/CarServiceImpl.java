package com.autosale.service;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.autosale.dao.CarDao;
import com.autosale.dao.UserDao;
import com.autosale.model.Car;
import com.autosale.model.FuelType;
import com.autosale.model.GearType;
import com.autosale.service.interfaces.CarService;

@Service
@Transactional
public class CarServiceImpl implements CarService {

	private String imageUrl;

	@Autowired
	CarDao carDao;

	@Autowired
	UserDao userDao;

	public Car getCarById(String carId) {
		return carDao.getCarById(Integer.valueOf(carId));
	}

	public void addNewCar(Map<String, String> requestParams, MultipartFile image) {
		addUploadedImageToServerFolder(image);
		carDao.persistNewCar(setProperties(requestParams, imageUrl, getUserName()));
	}

	public List<Car> getCarsList(Map<String, String> requestParams) {
		return carDao.getCarsByCriteria(requestParams);
	}

	public Car setProperties(Map<String, String> requestParams, String imageUrl, String name) {
		Car car = new Car();
		car.setAdditionalInfo(requestParams.get("additionalInfo"));
		car.setBrand(requestParams.get("brand"));
		car.setColour(requestParams.get("colour"));
		car.setEngineVolume(requestParams.get("engineVolume"));
		car.setFuel(FuelType.valueOf(requestParams.get("fuelType").toUpperCase(Locale.ENGLISH)));
		car.setGear(GearType.valueOf(requestParams.get("gearType").toUpperCase(Locale.ENGLISH)));
		car.setImage_url(imageUrl);
		car.setModel(requestParams.get("model"));
		car.setYear(requestParams.get("year"));
		car.setDate(new Timestamp(System.currentTimeMillis()));
		car.setMileage(requestParams.get("mileage"));
		car.setUser(userDao.getUserByName(name));
		return car;

	}

	public void addUploadedImageToServerFolder(MultipartFile image) {
		if (!image.isEmpty()) {
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
				setImageUrl(serverFile.getAbsolutePath());
			} catch (Exception e) {
			}
		}
	}

	public String getUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth.getName();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}
