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

import student.service.dto.ExamPeriodDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.service.ExamPeriodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/examperiods")
public class ExamPeriodRestController {

	@Autowired
	private ExamPeriodService examPeriodService;

	@GetMapping()
	public @ResponseBody ResponseEntity<List<ExamPeriodDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.findAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ExamPeriodDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.findByPage(pageable));
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
		Optional<ExamPeriodDto> examPeriodDto = examPeriodService.findById(id);
		if (examPeriodDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid exam period id!");
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ExamPeriodDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.save(professorDto));
		} catch (ExistEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ExamPeriodDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(examPeriodService.update(professorDto));
		} catch (EntityNotPresent ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		examPeriodService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
}
