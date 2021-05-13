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

import student.service.dto.TitleDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.service.TitleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/titles")
public class TitleRestController {

	@Autowired
	private TitleService titleService;

	@GetMapping()
	public @ResponseBody ResponseEntity<List<TitleDto>> getAll() {
		return ResponseEntity.status(HttpStatus.OK).body(titleService.findAll());
	}
	
	@GetMapping("/page")
	public @ResponseBody ResponseEntity<Page<TitleDto>> getByPage(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.OK).body(titleService.findByPage(pageable));
	}

	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> get(@PathVariable Long id) {
		Optional<TitleDto> titleDto = titleService.findById(id);
		if (titleDto.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(titleDto.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid title id!");
	}

	@PostMapping()
	public @ResponseBody ResponseEntity<Object> save(@Valid @RequestBody TitleDto titleDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(titleService.save(titleDto));
		} catch (ExistEntityException ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public @ResponseBody ResponseEntity<Object> update(@PathVariable Long id, @Valid @RequestBody TitleDto titleDto) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(titleService.update(titleDto));
		} catch (EntityNotPresent ex) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<String> delete(@PathVariable Long id) {
		titleService.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}

}

