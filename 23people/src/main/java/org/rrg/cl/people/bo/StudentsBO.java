package org.rrg.cl.people.bo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.rrg.cl.people.exceptions.NotFoundException;
import org.rrg.cl.people.model.Courses;
import org.rrg.cl.people.model.Response;
import org.rrg.cl.people.model.Students;
import org.rrg.cl.people.repository.StudentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentsBO {

private static String OK = "OK";
	
	@Autowired
    private StudentsRepository repository;
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws NotFoundException 
	 */
	public Students findByCode(String code) throws NotFoundException {
		
		Students students = new Students();
		
		Optional<org.rrg.cl.people.entities.Students> studentsEntity = repository.findById(code);
		
		if (studentsEntity.isPresent() ) {
			students.setAge(studentsEntity.get().getAge() );
			students.setCourse(studentsEntity.get().getCourse() );
			students.setLastName(studentsEntity.get().getLastName() );
			students.setName(studentsEntity.get().getName() );
			students.setRut(studentsEntity.get().getRut() );
		} else {
			throw new NotFoundException("404");
		}
		
		return students;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Students> findByName(String name) {
		
		List<org.rrg.cl.people.entities.Students> listStudentsEntity = repository.findByName(name);
		
		List<Students> listStudents= new ArrayList<>();
		
		for ( org.rrg.cl.people.entities.Students row : listStudentsEntity  )  {
			
			Students students = new Students();
			students.setAge(row.getAge() );
			students.setCourse(row.getCourse() );
			students.setLastName(row.getLastName() );
			students.setName(row.getName() );
			students.setRut(row.getRut() );
			
			listStudents.add(students);
			
		}
		
		return listStudents;
		
	}
	
	/**
	 * 
	 * @param students
	 * @return
	 */
	public Response createStudents(Students students) {
		
		Response response = new Response();
		
		org.rrg.cl.people.entities.Students studentsEntity = new org.rrg.cl.people.entities.Students();  
		
		studentsEntity.setAge(students.getAge() );
		studentsEntity.setCourse(students.getCourse() );
		studentsEntity.setLastName(students.getLastName() );
		studentsEntity.setName(students.getName() );
		studentsEntity.setRut(students.getRut() );
		
		repository.save(studentsEntity);
		
		return response;  
		
	}
	
	/**
	 * 
	 * @param students
	 * @return
	 */
	public Response updateStudents(Students students) {
		
		Response response = new Response();
		
		org.rrg.cl.people.entities.Students studentsEntity = new org.rrg.cl.people.entities.Students();  
		
		studentsEntity.setAge(students.getAge() );
		studentsEntity.setCourse(students.getCourse() );
		studentsEntity.setLastName(students.getLastName() );
		studentsEntity.setName(students.getName() );
		studentsEntity.setRut(students.getRut() );
		
		repository.save(studentsEntity);
		
		return response; 
		
	}
	
}
