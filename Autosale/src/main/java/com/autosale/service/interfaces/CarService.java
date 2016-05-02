package com.autosale.service.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.autosale.model.Car;

public interface CarService {

	void addNewCar (Map<String, String> requestParams,  MultipartFile image);
	
	Car getCarById(String carId);

	List<Car> getCarsByCriteria(Map<String, String> requestParams);
}
