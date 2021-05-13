package student.service.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import student.service.dao.StudentDao;
import student.service.dto.StudentDto;
import student.service.entity.StudentEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.StudentMapper;
import student.service.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	private final StudentDao studentDao;

	private final StudentMapper studentMapper = Mappers.getMapper(StudentMapper.class);

	@Autowired
	public StudentServiceImpl(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public List<StudentDto> findAll() {
		List<StudentEntity> entites = (List)studentDao.findAll();
		return entites.stream().map(entity -> {
			return studentMapper.toStudentDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<StudentDto> findByPage(Pageable pageable) {
		Page<StudentDto> entites = studentDao.findAll(pageable).map(studentMapper::toStudentDto);
		return entites;
	}

	@Override
	public void deleteByIndexNumber(String id) {
		studentDao.deleteById(id);
	}

	@Override
	public StudentDto save(StudentDto studentDto) throws ExistEntityException {
		Optional<StudentEntity> entity = studentDao.findById(studentDto.getIndexNumber());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Student already exists!");
		}
		StudentEntity s = studentDao.save(studentMapper.toStudent(studentDto));
		return studentMapper.toStudentDto(s);
	}
	
	@Override
	public StudentDto update(StudentDto studentDto) throws EntityNotPresent {
		Optional<StudentEntity> entity = studentDao.findById(studentDto.getIndexNumber());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(studentDto.getIndexNumber(), "Student does not exist!");
		}
		StudentEntity s = studentDao.save(studentMapper.toStudent(studentDto));
		return studentMapper.toStudentDto(s);
		
	}

	@Override
	public Optional<StudentDto> findByIndexNumber(String id) {
		Optional<StudentEntity> student = studentDao.findByIndexNumber(id);
		if (student.isPresent()) {
			return Optional.of(studentMapper.toStudentDto(student.get()));
		}
		return Optional.empty();
	}

}