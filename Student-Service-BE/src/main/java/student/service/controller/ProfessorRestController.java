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

import student.service.entity.ProfessorEntity;
import student.service.service.ProfessorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/professors")
public class ProfessorRestController {

	@Autowired
	private ProfessorService professorService;

	@GetMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public List<ProfessorEntity> getAll() {
		return professorService.findAll();
	}
	
	@GetMapping("/page")
	//@PreAuthorize("hasRole('ADMIN')")
	public Page<ProfessorEntity> getByPage(Pageable pageable, String search) {
		return professorService.findByPage(pageable, search);
	}
	
	@GetMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public Optional<ProfessorEntity> get(@PathVariable Long id) {
		return professorService.findById(id);
	}

	@PostMapping()
	//@PreAuthorize("hasRole('ADMIN')")
	public ProfessorEntity save(@Valid @RequestBody ProfessorEntity professorEntity) {
		return professorService.save(professorEntity);
	}
	
	@PutMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ProfessorEntity update(@PathVariable Long id, @Valid @RequestBody ProfessorEntity professorEntity) {
		return professorService.update(professorEntity);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		professorService.deleteById(id);
	}
}
