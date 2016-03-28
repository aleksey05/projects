package com.money.dao.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.money.dao.AdvertisementDao;
import com.money.entitiy.Advertisement;

@Repository
@Transactional
public class AdvertisementDaoImpl implements AdvertisementDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<Advertisement> getAdvList() {
		return em.createQuery("SELECT a FROM Advertisement a", Advertisement.class).getResultList();
	}

	@Override
	public Advertisement getAdvById(String advId) {
		return em.find(Advertisement.class, Integer.valueOf(advId));
	}

	@Override
	public void addAdv(Advertisement adv) {
		em.persist(adv);

	}

}
