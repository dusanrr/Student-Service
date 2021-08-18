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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import student.service.entity.SubjectEntity;
import student.service.service.SubjectService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/subjects")
public class SubjectRestController {
	
	@Autowired
	private SubjectService subjectService;

	@GetMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public List<SubjectEntity> getAll() {
		return subjectService.findAll();
	}
	
	@GetMapping("/page")
	//@PreAuthorize("hasRole('ADMIN')")
	public Page<SubjectEntity> getByPage(Pageable pageable, @RequestParam(required = false) String search) {
		return subjectService.findByPage(pageable, search);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Optional<SubjectEntity> get(@PathVariable Long id) {
		return subjectService.findById(id);
	}

	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public SubjectEntity save(@Valid @RequestBody SubjectEntity subjectEntity) {
		return subjectService.save(subjectEntity);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public SubjectEntity update(@PathVariable Long id, @Valid @RequestBody SubjectEntity subjectEntity) {
		return subjectService.update(subjectEntity);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		subjectService.deleteById(id);
	}

}
