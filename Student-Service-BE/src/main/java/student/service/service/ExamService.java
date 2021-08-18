package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.ExamEntity;

public interface ExamService {
	
	List<ExamEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;

	ExamEntity save(ExamEntity examEntity) throws EntityExistsException;
	
	ExamEntity update(ExamEntity examEntity) throws EntityNotFoundException ;
	
	Optional<ExamEntity> findById(Long id);

	Page<ExamEntity> findByPage(Pageable pageable, String search);
	
	Optional<List<?>> findAllPosibleStudentExams(String indexNumber);
}
