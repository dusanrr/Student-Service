package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.ProfessorEntity;

public interface ProfessorService {

	List<ProfessorEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;

	ProfessorEntity save(ProfessorEntity professorEntity) throws EntityExistsException;
	
	ProfessorEntity update(ProfessorEntity professorEntity) throws EntityNotFoundException ;

	Optional<ProfessorEntity> findByEmail(String email);
	
	Optional<ProfessorEntity> findById(Long id);

	Page<ProfessorEntity> findByPage(Pageable pageable, String search);
}
