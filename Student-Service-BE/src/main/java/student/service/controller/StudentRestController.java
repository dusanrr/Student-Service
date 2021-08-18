package student.service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import student.service.entity.StudentEntity;
import student.service.service.StudentService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/students")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@GetMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public List<StudentEntity> getAll() {
		return studentService.findAll();
	}
	
	@GetMapping("/page")
	//@PreAuthorize("hasRole('ADMIN')")
	public Page<StudentEntity> getByPage(Pageable pageable, String search) {
		return studentService.findByPage(pageable, search);
	}

	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Optional<StudentEntity> get(@PathVariable String id) {
		return studentService.findByIndexNumber(id);
	}

	@PostMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public StudentEntity save(@Valid @RequestBody StudentEntity studentEntity) {
		return studentService.save(studentEntity);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public StudentEntity update(@PathVariable String id, @Valid @RequestBody StudentEntity studentEntity) {
		return studentService.update(studentEntity);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable String id) {
		studentService.deleteByIndexNumber(id);
	}

}
