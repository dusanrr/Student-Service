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

import student.service.dto.ProfessorDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.service.ProfessorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/professors")
public class ProfessorRestController {

	@Autowired
	private ProfessorService professorService;

	@GetMapping()
	public @ResponseBody ResponseEntity<List<ProfessorDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.findAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<ProfessorDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(professorService.findByPage(pageable));
	}
	
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
		Optional<ProfessorDto> professorDto = professorService.findById(id);
		if (professorDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(professorDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid professor id!");
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(professorService.save(professorDto));
		} catch (ExistEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody ProfessorDto professorDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(professorService.update(professorDto));
		} catch (EntityNotPresent ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		professorService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
}
