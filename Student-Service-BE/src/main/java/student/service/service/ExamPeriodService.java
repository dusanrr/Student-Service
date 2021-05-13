package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.ExamPeriodDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface ExamPeriodService {

	List<ExamPeriodDto> findAll();

	void deleteById(Long id);

	ExamPeriodDto save(ExamPeriodDto examPeriodDto) throws ExistEntityException;
	
	ExamPeriodDto update(ExamPeriodDto examPeriodDto) throws EntityNotPresent ;
	
	Optional<ExamPeriodDto> findById(Long id);
	
	Optional<ExamPeriodDto> findByName(String name);

	Page<ExamPeriodDto> findByPage(Pageable pageable);
}
