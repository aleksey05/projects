package com.autosale.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="models")
public class model {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="modelId")
	private int id;
	private String name;
	private int age;
	private int hight;

	public model() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getHight() {
		return hight;
	}

	public void setHight(int hight) {
		this.hight = hight;
	
	
	}

	@Override
	public String toString() {
		return "model [id=" + id + ", name=" + name + ", age=" + age + ", hight=" + hight + "]";
	}
	
}
