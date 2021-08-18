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

import student.service.entity.ExamEntity;
import student.service.service.ExamService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/exams")
public class ExamRestController {
	
	@Autowired
	private ExamService examService;

	@GetMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public List<ExamEntity> getAll() {
		return examService.findAll();
	}
	
	@GetMapping("/page")
	//@PreAuthorize("hasRole('ADMIN')")
	public Page<ExamEntity> getByPage(Pageable pageable, String search) {
		return examService.findByPage(pageable, search);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Optional<ExamEntity> get(@PathVariable Long id) {
		return examService.findById(id);
	}
	
	@GetMapping("/indexNumber")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<List<?>> getStudentExam(String indexNumber) {
		return examService.findAllPosibleStudentExams(indexNumber);
	}

	@PostMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public ExamEntity save(@Valid @RequestBody ExamEntity examEntity) {
		return examService.save(examEntity);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ExamEntity update(@PathVariable Long id, @Valid @RequestBody ExamEntity examEntity) {
		return examService.update(examEntity);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		examService.deleteById(id);
	}

}
