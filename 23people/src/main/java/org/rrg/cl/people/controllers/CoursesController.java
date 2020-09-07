package org.rrg.cl.people.controllers;

import org.rrg.cl.people.bo.CoursesBO;
import org.rrg.cl.people.exceptions.NotFoundException;
import org.rrg.cl.people.model.Courses;
import org.rrg.cl.people.model.Response;
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
public class CoursesController {

	@Autowired
	private CoursesBO coursesBO;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "/courses", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Courses> course(@RequestParam(name = "id", required = false ) String id ) {
		Courses course = new Courses(); 
		try {
			course = coursesBO.findByCode(id);
			return new ResponseEntity<Courses>(course, HttpStatus.OK);
		} 
		catch (NotFoundException e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND );
		} 
		catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}

	
	/**
	 * 
	 * @param name
	 * @return
	 */
	/*
	@GetMapping(path = "/courses", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Courses>> courses(@RequestParam(name = "name", required = false ) String name ) {
		List<Courses> listCourse = coursesBO.findByName(name);
		return new ResponseEntity<List<Courses>>(listCourse, HttpStatus.OK);
	}
	*/
	
	/**
	 * 
	 * @param courses
	 * @return
	 */
	@PostMapping(path = "/courses", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity createCourse(@RequestBody Courses courses) {
		try {
			Response response = coursesBO.createCuorse(courses);
			return new ResponseEntity(HttpStatus.CREATED );
		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}
	
	/**
	 * 
	 * @param courses
	 * @return
	 */
	@PutMapping(path = "/courses", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateCourse(@RequestBody Courses courses) {
		try {
			Response response = coursesBO.updateCuorse(courses);
			return new ResponseEntity(HttpStatus.OK );
		
		} catch (Exception ex) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR );
		}
	}
	
}
