package com.money.entitiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "advertisements")
public class Advertisement {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int sum;
	private String user_name;
	private String adv_text;
	private String tel_number;

	public Advertisement() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getAdv_text() {
		return adv_text;
	}

	public void setAdv_text(String adv_text) {
		this.adv_text = adv_text;
	}

	public String getTel_number() {
		return tel_number;
	}

	public void setTel_number(String tel_number) {
		this.tel_number = tel_number;
	}

}
