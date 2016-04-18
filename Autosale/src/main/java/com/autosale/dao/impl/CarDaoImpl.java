package com.autosale.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.autosale.dao.CarDao;
import com.autosale.model.Car;
import com.autosale.model.GearType;

@Repository
public class CarDaoImpl implements CarDao {

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Car> getCarsByCriteria(Map<String, String> requestParams) {
		List<Car> list = em.createQuery(sortSearchParams(requestParams)).getResultList();
		return list;
	}

	@Transactional
	public void addNewCar(Car newCar) {
		em.persist(newCar);
	}

	@Transactional
	public Car getCarById(int carId) {
		return em.find(Car.class, carId);

	}

	private CriteriaQuery<Car> sortSearchParams(Map<String, String> requestParams) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Car> criteriaQuery = criteriaBuilder.createQuery(Car.class);
		Root<Car> car = criteriaQuery.from(Car.class);
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (!requestParams.get("gearType").isEmpty()) {
			predicates.add(criteriaBuilder.equal(car.get("gear"),
					GearType.valueOf(requestParams.get("gearType").toUpperCase(Locale.ENGLISH))));
		}
		if (!requestParams.get("mileageFrom").isEmpty()) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(car.get("mileage"), requestParams.get("mileageFrom")));
		}
		if (!requestParams.get("mileageTo").isEmpty()) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(car.get("mileage"), requestParams.get("mileageTo")));
		}
		if (!requestParams.get("fuelType").isEmpty()) {
			predicates.add(criteriaBuilder.equal(car.get("fuel"),
					GearType.valueOf(requestParams.get("fuelType").toUpperCase(Locale.ENGLISH))));
		}
		if (!requestParams.get("yearFrom").isEmpty()) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(car.get("year"), requestParams.get("yearFrom")));
		}
		if (!requestParams.get("yearTo").isEmpty()) {
			predicates.add(criteriaBuilder.lessThanOrEqualTo(car.get("year"), requestParams.get("yearTo")));
		}
		if (!requestParams.get("model").isEmpty()) {
			predicates.add(criteriaBuilder.equal(car.get("model"), requestParams.get("model")));
		}
		if (!requestParams.get("colour").isEmpty()) {
			predicates.add(criteriaBuilder.equal(car.get("colour"), requestParams.get("colour")));
		}
		if (!requestParams.get("brand").isEmpty()) {
			predicates.add(criteriaBuilder.equal(car.get("brand"), requestParams.get("brand")));
		}
		if (!requestParams.get("engineVolumeFrom").isEmpty()) {
			predicates.add(criteriaBuilder.greaterThanOrEqualTo(car.get("engineVolume"),
					requestParams.get("engineVolumeFrom")));
		}
		if (!requestParams.get("engineVolumeTo").isEmpty()) {
			predicates.add(
					criteriaBuilder.lessThanOrEqualTo(car.get("engineVolume"), requestParams.get("engineVolumeTo")));
		}
		criteriaQuery.select(car).where(predicates.toArray(new Predicate[] {}));

		return criteriaQuery;

	}

}
