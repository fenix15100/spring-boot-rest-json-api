package com.fenix15100.app.resource.schools;

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


public class SchoolRepositoryImpl extends ResourceRepositoryBase<School, Integer> implements ResourceRepositoryV2<School, Integer> {

	// for simplicity we make use of static, should not be used in real applications
	private static final Map<Integer, School> schools = new ConcurrentHashMap<>();

	private static final AtomicInteger ID_GENERATOR = new AtomicInteger(1);

	public SchoolRepositoryImpl() {
		super(School.class);
	}

	@Override
	public <S extends School> S save(S entity) {
		if (entity.getId() == null) {
			entity.setId(ID_GENERATOR.getAndIncrement());
		}
		schools.put(entity.getId(), entity);
		return entity;
	}

	@Override
	public <S extends School> S create(S entity) {
		if (entity.getId() != null && schools.containsKey(entity.getId())) {
			throw new BadRequestException("School already exists");
		}
		return save(entity);
	}

	@Override
	public Class<School> getResourceClass() {
		return School.class;
	}

	@Override
	public School findOne(Integer schoolId, QuerySpec querySpec) {
		School school = schools.get(schoolId);
		if (school == null) {
			throw new ResourceNotFoundException("School not found!");
		}
		return school;
	}

	@Override
	public ResourceList<School> findAll(QuerySpec querySpec) {
		return querySpec.apply(schools.values());
	}


	@Override
	public void delete(Integer schoolId) {
		schools.remove(schoolId);
	}

	
}
