package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.ExamPeriodEntity;

public interface ExamPeriodService {

	List<ExamPeriodEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;

	ExamPeriodEntity save(ExamPeriodEntity examPeriodEntity) throws EntityExistsException;
	
	ExamPeriodEntity update(ExamPeriodEntity examPeriodEntity) throws EntityNotFoundException ;
	
	Optional<ExamPeriodEntity> findById(Long id);
	
	Optional<ExamPeriodEntity> findByName(String name);

	Page<ExamPeriodEntity> findByPage(Pageable pageable, String search);
}
