package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.ExamRegistrationDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface ExamRegistrationService {

	List<ExamRegistrationDto> findAll();

	void deleteById(Long id);

	ExamRegistrationDto save(ExamRegistrationDto examRegistrationDto) throws ExistEntityException;
	
	ExamRegistrationDto update(ExamRegistrationDto examRegistrationDto) throws EntityNotPresent ;
	
	Optional<ExamRegistrationDto> findById(Long id);

	Page<ExamRegistrationDto> findByPage(Pageable pageable);
}