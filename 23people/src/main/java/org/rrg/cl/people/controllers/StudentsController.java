package org.rrg.cl.people.controllers;

import org.rrg.cl.people.bo.StudentsBO;
import org.rrg.cl.people.exceptions.NotFoundException;
import org.rrg.cl.people.model.Response;
import org.rrg.cl.people.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api")
public class StudentsController {

	@Autowired
	private StudentsBO studentsBO;
	
	/**
	 * 
	 * @param rut
	 * @return
	 */
	@GetMapping(path = "/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Students> students(@RequestParam(name = "rut", required = false ) String rut ) {
		
		Students students = new Students();
		try {
			students = studentsBO.findByCode(rut);
			return new ResponseEntity<Students>(students, HttpStatus.OK);
		} catch (NotFoundException ne) {
			return new ResponseEntity(HttpStatus.NOT_FOUND );
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	/*
	@GetMapping(path = "/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Students>> studentsName(@RequestParam(name = "name", required = false ) String name ) {
		
		List<Students> listStudents = studentsBO.findByName(name);
		
		return new ResponseEntity<List<Students>>(listStudents, HttpStatus.OK);
	}
	*/
	
	/**
	 * 
	 * @param students
	 * @return
	 */
	@PostMapping(path = "/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createStudent(@RequestBody Students students) {
		
		try {
			Response response = studentsBO.createStudents(students);
			return new ResponseEntity(HttpStatus.CREATED );
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
	}
	
	/**
	 * 
	 * @param students
	 * @return
	 */
	@PutMapping(path = "/students", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateStudent(@RequestBody Students students) {
		
		try {
			Response response = studentsBO.updateStudents(students);
			return new ResponseEntity(HttpStatus.OK );
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
		}
		
	}
		
}
	