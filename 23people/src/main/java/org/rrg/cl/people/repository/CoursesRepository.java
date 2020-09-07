package org.rrg.cl.people.repository;

import java.util.List;

import org.rrg.cl.people.entities.Courses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Courses, String> {
	
	List<Courses> findByName(String name);
	
} 
