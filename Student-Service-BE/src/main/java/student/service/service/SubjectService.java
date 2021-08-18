package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.SubjectEntity;

public interface SubjectService {
	
	List<SubjectEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;

	SubjectEntity save(SubjectEntity subjectEntity) throws EntityExistsException;
	
	SubjectEntity update(SubjectEntity subjectEntity) throws EntityNotFoundException ;

	Optional<SubjectEntity> findById(Long id);
	
	Optional<SubjectEntity> findByName(String name);

	Page<SubjectEntity> findByPage(Pageable pageable, String search);
}
