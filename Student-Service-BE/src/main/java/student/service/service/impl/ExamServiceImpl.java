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

import student.service.dao.ExamDao;
import student.service.entity.ExamEntity;
import student.service.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	private final ExamDao examDao;

	@Autowired
	public ExamServiceImpl(ExamDao examDao) {
		super();
		this.examDao = examDao;
	}
	
	@Override
	public List<ExamEntity> findAll() {
		List<ExamEntity> entites = (List<ExamEntity>)examDao.findAll();
		return entites.stream().map(entity -> {
			return (entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ExamEntity> findByPage(Pageable pageable, String search) {
		if (search == null)
			return examDao.findAll(pageable);
		else
			return examDao.findBySubjectNameContaining(pageable, search);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(examDao.existsById(id))
			examDao.deleteById(id);
		else
			throw new EntityNotFoundException("Exam with that ID does not exists!");
	}

	@Override
	public ExamEntity save(ExamEntity examEntity) throws EntityExistsException {
		Optional<ExamEntity> entity = examDao.findExamWithSubjectAndDate(examEntity.getSubject().getId());
		if (entity.isPresent()) {
			throw new EntityExistsException("There is currently an active exam for the selected subject!");
		}
		ExamEntity e = examDao.save(examEntity);
		return e;
	}
	
	@Override
	public ExamEntity update(ExamEntity examEntity) throws EntityNotFoundException {
		Optional<ExamEntity> entity = examDao.findById(examEntity.getId());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Exam does not exist!");
		}
		ExamEntity e = examDao.save(examEntity);
		return e;
		
	}
	
	@Override
	public Optional<ExamEntity> findById(Long id) {
		Optional<ExamEntity> exam = examDao.findById(id);
		if (exam.isPresent()) {
			return Optional.of(exam.get());
		}
		return Optional.empty();
	}

	@Override
	public Optional<List<?>> findAllPosibleStudentExams(String indexNumber) {
		Optional<List<?>> exam = examDao.findAllPosibleStudentExams(indexNumber);
		if (exam.isPresent()) {
			return Optional.of(exam.get());
		}
		return Optional.empty();
	}
}
