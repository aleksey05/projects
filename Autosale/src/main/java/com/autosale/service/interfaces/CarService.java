package com.autosale.service.interfaces;

import java.util.List;
import java.util.Map;

import com.autosale.model.Car;

public interface CarService {

	void addNewCar();

	List<Car> getCarsByCriteria(Map<String, String> requestParams);
}
