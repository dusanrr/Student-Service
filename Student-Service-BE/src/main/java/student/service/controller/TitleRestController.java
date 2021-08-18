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

import student.service.entity.TitleEntity;
import student.service.service.TitleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/titles")
public class TitleRestController {

	@Autowired
	private TitleService titleService;

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public List<TitleEntity> getAll() {
		return titleService.findAll();
	}
	
	@GetMapping("/page")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<TitleEntity> getByPage(Pageable pageable) {
		return titleService.findByPage(pageable);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<TitleEntity> get(@PathVariable Long id) {
		return titleService.findById(id);
	}

	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public TitleEntity save(@Valid @RequestBody TitleEntity titleEntity) {
		return titleService.save(titleEntity);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public TitleEntity update(@PathVariable Long id, @Valid @RequestBody TitleEntity titleEntity) {
		return titleService.update(titleEntity);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		titleService.deleteById(id);
	}

}

