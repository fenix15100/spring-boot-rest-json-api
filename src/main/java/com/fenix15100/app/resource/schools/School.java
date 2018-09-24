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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fenix15100.app.resource.course.Course;

import io.crnk.core.resource.annotations.JsonApiId;
import io.crnk.core.resource.annotations.JsonApiLinksInformation;
import io.crnk.core.resource.annotations.JsonApiMetaInformation;
import io.crnk.core.resource.annotations.JsonApiRelation;
import io.crnk.core.resource.annotations.JsonApiResource;
import io.crnk.core.resource.annotations.LookupIncludeBehavior;
import io.crnk.core.resource.annotations.RelationshipRepositoryBehavior;
import io.crnk.core.resource.links.LinksInformation;
import io.crnk.core.resource.meta.MetaInformation;





@JsonApiResource(type = "school", resourcePath = "schools")

@SuppressWarnings("serial")

@Entity
@Table(name="School")
public class School implements Serializable{

	@JsonApiId
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_school")
	private  Integer id;
	
	@JsonProperty
	private String name;
	
	@JsonProperty
	private String street;
	
	
	@JsonApiRelation(opposite = "school", lookUp = LookupIncludeBehavior.AUTOMATICALLY_WHEN_NULL,
			repositoryBehavior = RelationshipRepositoryBehavior.FORWARD_OPPOSITE)
	@OneToMany(mappedBy="school",cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(nullable=true)
	
	private Set<Course> courses;
	
	@Transient
	@JsonApiMetaInformation
	private SchoolMeta meta;

	public static class SchoolMeta implements MetaInformation {

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
	private SchoolLinks links;

	public static class SchoolLinks implements LinksInformation {

		private String value;

		public String getValue() {
			return value;
		}

		public void setValue(String value) {
			this.value = value;
		}
	}

	
	
	protected School() {
		
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
	
	
	
	

}
