package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.ExamRegistrationEntity;

public interface ExamRegistrationService {

	List<ExamRegistrationEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;;

	ExamRegistrationEntity save(ExamRegistrationEntity examRegistrationEntity) throws EntityExistsException;
	
	ExamRegistrationEntity update(ExamRegistrationEntity examRegistrationEntity) throws EntityNotFoundException ;
	
	Optional<ExamRegistrationEntity> findById(Long id);

	Page<ExamRegistrationEntity> findByPage(Pageable pageable, String search);
}
