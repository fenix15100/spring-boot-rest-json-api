package com.fenix15100.app.resource.course;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


import org.springframework.stereotype.Component;



import io.crnk.core.exception.BadRequestException;
import io.crnk.core.exception.ResourceNotFoundException;
import io.crnk.core.queryspec.QuerySpec;
import io.crnk.core.repository.ResourceRepositoryBase;
import io.crnk.core.repository.ResourceRepositoryV2;
import io.crnk.core.resource.list.ResourceList;


public class CourseRepositoryImpl extends ResourceRepositoryBase<Course, Integer> implements ResourceRepositoryV2<Course, Integer>{

	// for simplicity we make use of static, should not be used in real applications
	private static final Map<Integer, Course> courses = new ConcurrentHashMap<>();

	private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

	public CourseRepositoryImpl() {
		super(Course.class);
	}

	@Override
	public <S extends Course> S save(S entity) {
		if (entity.getId() == null) {
			entity.setId(ID_GENERATOR.getAndIncrement());
		}
		courses.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends Course> S create(S entity) {
		if (entity.getId() != null && courses.containsKey(entity.getId())) {
			throw new BadRequestException("Task already exists");
		}
		return save(entity);
	}

	@Override
	public Class<Course> getResourceClass() {
		return Course.class;
	}

	@Override
	public Course findOne(Integer courseId, QuerySpec querySpec) {
		Course course = courses.get(courseId);
		if (course == null) {
			throw new ResourceNotFoundException("Course not found!");
		}
		return course;
	}

	@Override
	public ResourceList<Course> findAll(QuerySpec querySpec) {
		return querySpec.apply(courses.values());
	}


	@Override
	public void delete(Integer courseId) {
		courses.remove(courseId);
	}

	
}
