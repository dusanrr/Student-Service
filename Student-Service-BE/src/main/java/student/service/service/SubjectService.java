package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.SubjectDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface SubjectService {
	
	List<SubjectDto> findAll();

	void deleteById(Long id);

	SubjectDto save(SubjectDto subjectDto) throws ExistEntityException;
	
	SubjectDto update(SubjectDto subjectDto) throws EntityNotPresent ;

	Optional<SubjectDto> findById(Long id);

	Page<SubjectDto> findByPage(Pageable pageable);
}