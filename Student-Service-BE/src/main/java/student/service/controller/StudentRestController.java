package student.service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import student.service.dto.StudentDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.service.StudentService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/students")
public class StudentRestController {

	@Autowired
	private StudentService studentService;

	@GetMapping()
	public @ResponseBody ResponseEntity<List<StudentDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<StudentDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(studentService.findByPage(pageable));
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> get(@PathVariable String id) {
		Optional<StudentDto> studentDto = studentService.findByIndexNumber(id);
		if (studentDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(studentDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid student index number!");
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody StudentDto studentDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.save(studentDto));
		} catch (ExistEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable String id, @Valid @RequestBody StudentDto studentDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(studentService.update(studentDto));
		} catch (EntityNotPresent ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable String id) {
		studentService.deleteByIndexNumber(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}
