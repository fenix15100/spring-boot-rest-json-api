package com.fenix15100.app.resource.course;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenix15100.app.resource.schools.School;


import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiLinksInformation;
import io.crnk.core.resource.annotations.JsonApiMetaInformation;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiRelationId;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.annotations.LookupIncludeBehavior;
import io.crnk.core.resource.annotations.RelationshipRepositoryBehavior;
import io.crnk.core.resource.annotations.SerializeType;
import io.crnk.core.resource.links.LinksInformation;
import io.crnk.core.resource.meta.MetaInformation;


@SuppressWarnings("serial")
@Entity
@Table(name="Courses")
@JsonApiResource(type = "course", resourcePath = "courses")
public class Course implements Serializable {
	@JsonApiId
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_course")
	private Integer id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private int grade;
	
	@JsonApiRelationId
	@Transient
	private Integer schoolId;
	
	@ManyToOne
	@JoinColumn(name="id_school", referencedColumnName="id_school",nullable=false)
	
	

	@JsonApiRelation(opposite = "courses", lookUp = LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,
			repositoryBehavior = RelationshipRepositoryBehavior.FORWARD_OWNER,
			serialize = SerializeType.ONLY_ID)
	private School school;
	
	// TODO implement Asignaturas, Alumnos ,Profesores (ManytoMany)

	@Transient
	@JsonApiMetaInformation
	private CourseMeta meta;

	public static class CourseMeta implements MetaInformation {

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	@Transient
	@JsonApiLinksInformation
	private CourseLinks links;

	public static class CourseLinks implements LinksInformation {

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}
	
	
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

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.schoolId = school != null ? school.getId() : null;
	    this.school = school;
	}

	public Integer getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(Integer id_school) {
		this.schoolId = id_school;
	    this.school = null;
	}

	
	
	
	
	

	
}
