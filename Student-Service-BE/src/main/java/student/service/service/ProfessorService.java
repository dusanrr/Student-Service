package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.ProfessorDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface ProfessorService {

	List<ProfessorDto> findAll();

	void deleteById(Long id);

	ProfessorDto save(ProfessorDto professorDto) throws ExistEntityException;
	
	ProfessorDto update(ProfessorDto professorDto) throws EntityNotPresent ;

	Optional<ProfessorDto> findByEmail(String email);
	
	Optional<ProfessorDto> findById(Long id);

	Page<ProfessorDto> findByPage(Pageable pageable);
}
