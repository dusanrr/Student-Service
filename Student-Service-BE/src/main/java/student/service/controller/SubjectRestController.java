package student.service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import student.service.dto.SubjectDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.service.SubjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/subjects")
public class SubjectRestController {
	
	@Autowired
	private SubjectService subjectService;

	@GetMapping()
	public @ResponseBody ResponseEntity<List<SubjectDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.findAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<SubjectDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(subjectService.findByPage(pageable));
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
		Optional<SubjectDto> subjectDto = subjectService.findById(id);
		if (subjectDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(subjectDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid subject id!");
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody SubjectDto subjectDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.save(subjectDto));
		} catch (ExistEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody SubjectDto subjectDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(subjectService.update(subjectDto));
		} catch (EntityNotPresent ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		subjectService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}
