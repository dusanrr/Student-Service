package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.ExamDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface ExamService {
	
	List<ExamDto> findAll();

	void deleteById(Long id);

	ExamDto save(ExamDto examDto) throws ExistEntityException;
	
	ExamDto update(ExamDto examDto) throws EntityNotPresent ;
	
	Optional<ExamDto> findById(Long id);

	Page<ExamDto> findByPage(Pageable pageable);
}