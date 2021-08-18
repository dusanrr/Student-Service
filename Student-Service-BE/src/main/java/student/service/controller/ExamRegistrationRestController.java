package student.service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import student.service.entity.ExamRegistrationEntity;
import student.service.service.ExamRegistrationService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/examregistrations")
public class ExamRegistrationRestController {

	@Autowired
	private ExamRegistrationService examRegistrationService;

	@GetMapping()
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public List<ExamRegistrationEntity> getAll() {
		return examRegistrationService.findAll();
	}
	
	@GetMapping("/page")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Page<ExamRegistrationEntity> getByPage(Pageable pageable, String search) {
		return examRegistrationService.findByPage(pageable, search);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public Optional<ExamRegistrationEntity> get(@PathVariable Long id) {
		return examRegistrationService.findById(id);
	}

	@PostMapping()
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ExamRegistrationEntity save(@Valid @RequestBody ExamRegistrationEntity examRegistrationEntity) {
		return examRegistrationService.save(examRegistrationEntity);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ExamRegistrationEntity update(@PathVariable Long id, @Valid @RequestBody ExamRegistrationEntity examRegistrationEntity) {
		return examRegistrationService.update(examRegistrationEntity);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		examRegistrationService.deleteById(id);
	}
}
