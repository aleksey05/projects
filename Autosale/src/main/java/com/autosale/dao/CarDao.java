package com.autosale.dao;

import java.util.List;
import java.util.Map;

import com.autosale.model.Car;

public interface CarDao {

	public List<Car> getCarsByCriteria(Map<String, String> requestParams);

	public void addNewCar(Car newCar);

	public Car getCarById(int id);

}