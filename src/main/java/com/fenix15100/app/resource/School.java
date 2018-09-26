package com.fenix15100.app.resource;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import com.yahoo.elide.annotation.Include;



@SuppressWarnings("serial")
@Entity
@Table(name="school")
@NamedQuery(name="School.findAll", query="SELECT c FROM School c")
public class School implements Serializable{


	private  Integer id;

	private String name;

	private String street;

	private Set<Course> courses;
	

	public School() {
	}

	public School(Integer id_school, String name, String street, Set<Course> courses) {
		super();
		this.id = id_school;
		this.name = name;
		this.street = street;
		this.courses = courses;
	}
	
	public School(String name, String street, Set<Course> courses) {
		super();
		this.name = name;
		this.street = street;
		this.courses = courses;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_school")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id_school) {
		this.id = id_school;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


	@OneToMany(mappedBy="school",cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(nullable=true)
	@JsonManagedReference
	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Course addChild(Course course) {
		getCourses().add(course);
		course.setSchool(this);
		return course;
	}
	public Course removeChild(Course course) {
		getCourses().remove(course);
		course.setSchool(null);
		return course;
	}








}
