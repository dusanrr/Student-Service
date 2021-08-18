package student.service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import student.service.dao.StudentDao;
import student.service.entity.StudentEntity;
import student.service.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentDao studentDao;

	@Autowired
	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<StudentEntity> findAll() {
		List<StudentEntity> entites = (List<StudentEntity>)studentDao.findAll();
		return entites.stream().map(entity -> {
			return entity;
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<StudentEntity> findByPage(Pageable pageable, String search) {
		if (search == null)
			return studentDao.findAll(pageable);
		else
			return studentDao.findByIndexNumberContaining(pageable, search);
	}

	@Override
	public void deleteByIndexNumber(String id) throws EntityNotFoundException {
		if(studentDao.existsByIndexNumber(id))
			studentDao.deleteByIndexNumber(id);
		else
			throw new EntityNotFoundException("Student with that index number does not exists!");
	}

	@Override
	public StudentEntity save(StudentEntity studentEntity) throws EntityExistsException {
		Optional<StudentEntity> entity = studentDao.findByIndexNumber(studentEntity.getIndexNumber());
		if (entity.isPresent()) {
			throw new EntityExistsException("Student already exists!");
		}
		StudentEntity s = studentDao.save(studentEntity);
		return s;
	}
	
	@Override
	public StudentEntity update(StudentEntity studentEntity) throws EntityNotFoundException {
		Optional<StudentEntity> entity = studentDao.findById(studentEntity.getId());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Student does not exist!");
		}
		StudentEntity s = studentDao.save(studentEntity);
		return s;
		
	}

	@Override
	public Optional<StudentEntity> findByIndexNumber(String id) {
		Optional<StudentEntity> student = studentDao.findByIndexNumber(id);
		if (student.isPresent()) {
			return Optional.of(student.get());
		}
		return Optional.empty();
	}

}
