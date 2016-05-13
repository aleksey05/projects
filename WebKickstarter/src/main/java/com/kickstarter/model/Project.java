package com.kickstarter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(name = "projects")
//@Cacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectId")
	private int id;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "daysLeft")
	private int daysLeft;
	@Column(name = "requiredSum")
	private int requiredSum;
	@Column(name = "gainedSum")
	private int gainedSum;
	@Column(name = "projectHistory")
	private String projectHistory;
	@Column(name = "videoLink")
	private String videoLink;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.DETACH})
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@OneToMany(mappedBy = "project", cascade = {CascadeType.ALL})
	private Collection<Question> questions = new ArrayList<>();
	
	@OneToMany(mappedBy = "project", cascade={CascadeType.ALL})
	private List<Payment> payments = new ArrayList<Payment>();

	public Project() {
	}

	public Project(int id, String title, String description, int daysLeft, int requiredSum, int gainedSum,// FIXME
			String projectHistory, String videoLink, Category category) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.daysLeft = daysLeft;
		this.requiredSum = requiredSum;
		this.gainedSum = gainedSum;
		this.projectHistory = projectHistory;
		this.videoLink = videoLink;
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

	public Collection<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Collection<Question> questions) {
		this.questions = questions;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return description;
	}

	public void setDiscription(String discription) {
		this.description = discription;
	}

	public int getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}

	public int getRequiredSum() {
		return requiredSum;
	}

	public void setRequiredSum(int requiredSum) {
		this.requiredSum = requiredSum;
	}

	public int getGainedSum() {
		return gainedSum;
	}

	public void setGainedSum(int gainedSum) {
		this.gainedSum = gainedSum;
	}

	public String getProjectHistory() {
		return projectHistory;
	}

	public void setProjectHistory(String projectHistory) {
		this.projectHistory = projectHistory;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ("Project Title : " + title + "\n Project Discription :" + description + "\n Project History : "
				+ projectHistory + "\n Video Link : " + videoLink + "\n Required Sum :" + requiredSum
				+ "\n Gained Sum :" + gainedSum + "\n Days Left :" + daysLeft + "\n  ProjectId :" + id + "\n ");
	}

}