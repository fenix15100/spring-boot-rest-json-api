package com.fenix15100.app.resource;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.NamedQuery;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fenix15100.app.resource.School;


import com.yahoo.elide.annotation.Include;



@SuppressWarnings("serial")
@Entity
@Table(name="course")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")

public class Course implements Serializable {


	private Integer id;
	

	private String name;
	

	private int grade;
	

	

	private School school;
	
	// TODO implement Asignaturas, Alumnos ,Profesores (ManytoMany)


	public Course() {


	}

	public Course(Integer id, String name, int grade, School school) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.school = school;
	}
	
	public Course(String name, int grade, School school) {
		this.name = name;
		this.grade = grade;
		this.school = school;
	}
	
	public Course(String name, int grade) {
		this.name = name;
		this.grade = grade;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_course")
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

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@ManyToOne
	@JoinColumn(name="id_school", referencedColumnName="id_school",nullable=false)
	@JsonBackReference
	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}
}
