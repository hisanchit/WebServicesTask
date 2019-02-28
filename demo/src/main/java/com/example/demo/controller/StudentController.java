package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.*;
import com.example.demo.repository.*;


@RestController
@RequestMapping("/")
public class StudentController {
	
	StudentModel studentModel;
	
	@Autowired
	StudentRepo studentRepo;

	@GetMapping("students")
	private List<StudentModel> getAll() {
		return studentRepo.findAll();
	}
	
	@PostMapping("addStudent")
	private StudentModel createStudent(@Valid @RequestBody StudentModel studM) {
		return studentRepo.save(studM);
	}
	
	@DeleteMapping("students/{id}")
	public void deleteStudent(@PathVariable long id) {
		studentRepo.deleteById(id);
	}
	
	@PutMapping("students/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody StudentModel student, @PathVariable long id) {

		Optional<StudentModel> studentOptional = studentRepo.findById(id);

		if (!studentOptional.isPresent())
			return ResponseEntity.notFound().build();

		student.setId(id);
		
		studentRepo.save(student);

		return ResponseEntity.noContent().build();
	}

	/*@GetMapping("/students/{id}")
	public Student retrieveStudent(@PathVariable long id) {
		Optional<Student> student = studentRepository.findById(id);

		if (!student.isPresent())
			throw new StudentNotFoundException("id-" + id);

		return student.get();
	}

	@PostMapping("/students")
	public ResponseEntity<Object> createStudent(@RequestBody Student student) {
		Student savedStudent = studentRepository.save(student);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedStudent.getId()).toUri();

		return ResponseEntity.created(location).build();

	}
	
	*/
}
