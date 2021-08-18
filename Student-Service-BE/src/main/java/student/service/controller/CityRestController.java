package student.service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import student.service.entity.CityEntity;
import student.service.service.CityService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/cities")
public class CityRestController {

	@Autowired
	private CityService cityService;

	@GetMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public List<CityEntity> getAll() {
		return cityService.findAll();
	}
	
	@GetMapping("/page")
	@PreAuthorize("hasRole('ADMIN')")
	public Page<CityEntity> getByPage(Pageable pageable) {
		return cityService.findByPage(pageable);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public Optional<CityEntity> get(@PathVariable Long id) {
		return cityService.findById(id);
	}

	@PostMapping()
	@PreAuthorize("hasRole('ADMIN')")
	public CityEntity save(@Valid @RequestBody CityEntity cityEntity) {
		return cityService.save(cityEntity);
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public CityEntity update(@PathVariable Long id, @Valid @RequestBody CityEntity cityEntity) {
		return cityService.update(cityEntity);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public void delete(@PathVariable Long id) {
		cityService.deleteById(id);
	}

}
