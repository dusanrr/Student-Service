package student.service.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import student.service.dto.CityDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.service.CityService;

import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/cities")
public class CityRestController {

	@Autowired
	private CityService cityService;

	@GetMapping()
	public @ResponseBody ResponseEntity<List<CityDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(cityService.findAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<CityDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(cityService.findByPage(pageable));
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
		Optional<CityDto> cityDto = cityService.findById(id);
		if (cityDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(cityDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid city id!");
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody CityDto cityDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cityService.save(cityDto));
		} catch (ExistEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody CityDto cityDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cityService.update(cityDto));
		} catch (EntityNotPresent ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		cityService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}
