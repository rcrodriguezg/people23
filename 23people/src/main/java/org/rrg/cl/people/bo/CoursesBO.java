package org.rrg.cl.people.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.rrg.cl.people.exceptions.NotFoundException;
import org.rrg.cl.people.model.Courses;
import org.rrg.cl.people.model.Response;
import org.rrg.cl.people.repository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesBO {

	private static String OK = "OK";
	
	@Autowired
    private CoursesRepository repository;

	/**
	 * 
	 * @param code
	 * @return
	 * @throws NotFoundException 
	 */
	public Courses findByCode(String code) throws NotFoundException {
		Courses courses = new Courses();
		
		Optional<org.rrg.cl.people.entities.Courses> curseEntity = repository.findById(code);
		
		if (curseEntity.isPresent() ) {
			courses.setCode(curseEntity.get().getCode() );
			courses.setName(curseEntity.get().getName() );
		} else {
			throw new NotFoundException("404");
		}
		return courses; 
	}
	
	public void deleteByCode(String code)  {
		Courses courses = new Courses();
		repository.deleteById(code);
		
	}
	
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Courses> findByName(String name) {
		List<Courses> listCourses = new ArrayList<>();
		List<org.rrg.cl.people.entities.Courses> listCurseEntity = repository.findByName(name);
		for ( org.rrg.cl.people.entities.Courses row : listCurseEntity ) {
			Courses courses = new Courses(); 
			courses.setCode(row.getCode() );
			courses.setName(row.getName() );
			listCourses.add(courses);
		}
		return listCourses; 
	}
	
	/**
	 * 
	 * @param courses
	 * @return
	 */
	public Response createCuorse(Courses courses) {
		Response response = new Response();
		response.setCode(202);
		response.setMessage(OK);
		org.rrg.cl.people.entities.Courses coursesEntity = new org.rrg.cl.people.entities.Courses(); 
		coursesEntity.setCode(courses.getCode() );
		coursesEntity.setName(courses.getName());
		repository.save(coursesEntity);
		return response;
	} 
	
	/**
	 * 
	 * @param courses
	 * @return
	 */
	public Response updateCuorse(Courses courses) {
		Response response = new Response();
		response.setCode(202);
		response.setMessage(OK);
		org.rrg.cl.people.entities.Courses coursesEntity = new org.rrg.cl.people.entities.Courses(); 
		coursesEntity.setCode(courses.getCode() );
		coursesEntity.setName(courses.getName());
		repository.save(coursesEntity);
		return response;
		
	}
	
}
