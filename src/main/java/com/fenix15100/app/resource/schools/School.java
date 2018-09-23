package com.fenix15100.app.resource.schools;


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

import com.fenix15100.app.resource.course.Course;


@SuppressWarnings("serial")
@Entity
@Table(name="School")



public class School implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_school")
	private  Integer id;
	
	
	private String name;
	
	
	private String street;
	
	@OneToMany(mappedBy="school",cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(nullable=true)
	
	
	private Set<Course> courses;

	
	
	protected School() {
		
	}

	public School(Integer id, String name, String street, Set<Course> courses) {
		super();
		this.id = id;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
	
	
	
	

}
