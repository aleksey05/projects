package com.money.dao;

import java.util.List;
import com.money.entitiy.Advertisement;

public interface AdvertisementDao {
	List<Advertisement> getAdvList();

	Advertisement getAdvById(String advId);

	void addAdv(Advertisement adv);
}
