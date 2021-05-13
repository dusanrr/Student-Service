package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.StudentDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface StudentService {

	List<StudentDto> findAll();

	void deleteByIndexNumber(String id);

	StudentDto save(StudentDto studentDto) throws ExistEntityException;
	
	StudentDto update(StudentDto studentDto) throws EntityNotPresent ;

	Optional<StudentDto> findByIndexNumber(String id);

	Page<StudentDto> findByPage(Pageable pageable);
}
