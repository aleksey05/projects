package com.money.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.money.dao.AdvertisementDao;
import com.money.entitiy.Advertisement;

@RestController
@RequestMapping("/adv")
@Transactional
public class AdvertisementController {

	@Autowired
	AdvertisementDao advDao;

	@RequestMapping("/list")
	public List<Advertisement> showAdvList() {
		return advDao.getAdvList();
	}

	@RequestMapping("/list/{advId}")
	public Advertisement showAdvById(@PathVariable String advId) {
		return advDao.getAdvById(advId);
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public List<Advertisement> addAdv(@RequestBody Advertisement adv) {
		advDao.addAdv(adv);
		return advDao.getAdvList();
	}

}