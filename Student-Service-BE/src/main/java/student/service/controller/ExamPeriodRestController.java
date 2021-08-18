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

import student.service.entity.ExamPeriodEntity;
import student.service.service.ExamPeriodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/examperiods")
public class ExamPeriodRestController {

	@Autowired
	private ExamPeriodService examPeriodService;

	@GetMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public List<ExamPeriodEntity> getAll() {
		return examPeriodService.findAll();
	}
	
	@GetMapping("/page")
	//@PreAuthorize("hasRole('ADMIN')")
	public Page<ExamPeriodEntity> getByPage(Pageable pageable, String search) {
		return examPeriodService.findByPage(pageable, search);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Optional<ExamPeriodEntity> get(@PathVariable Long id) {
		return examPeriodService.findById(id);
	}

	@PostMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public ExamPeriodEntity save(@Valid @RequestBody ExamPeriodEntity professorDto) {
		return examPeriodService.save(professorDto);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ExamPeriodEntity update(@PathVariable Long id, @Valid @RequestBody ExamPeriodEntity professorDto) {
		return examPeriodService.update(professorDto);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		examPeriodService.deleteById(id);
	}
}
