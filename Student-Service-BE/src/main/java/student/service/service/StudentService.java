package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.StudentEntity;

public interface StudentService {

	List<StudentEntity> findAll();

	void deleteByIndexNumber(String id) throws EntityNotFoundException;

	StudentEntity save(StudentEntity studentEntity) throws EntityExistsException;
	
	StudentEntity update(StudentEntity studentEntity) throws EntityNotFoundException ;

	Optional<StudentEntity> findByIndexNumber(String id);

	Page<StudentEntity> findByPage(Pageable pageable, String search);
}
