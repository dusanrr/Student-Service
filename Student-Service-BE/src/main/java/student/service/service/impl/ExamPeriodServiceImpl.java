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

import student.service.dao.ExamPeriodDao;
import student.service.entity.ExamPeriodEntity;
import student.service.service.ExamPeriodService;

@Service
@Transactional
public class ExamPeriodServiceImpl implements ExamPeriodService {

	private final ExamPeriodDao examPeriodDao;

	@Autowired
	public ExamPeriodServiceImpl(ExamPeriodDao examPeriodDao) {
		super();
		this.examPeriodDao = examPeriodDao;
	}

	
	@Override
	public List<ExamPeriodEntity> findAll() {
		List<ExamPeriodEntity> entites = (List<ExamPeriodEntity>)examPeriodDao.findAll();
		return entites.stream().map(entity -> {
			return (entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ExamPeriodEntity> findByPage(Pageable pageable, String search) {
		if (search == null)
			return examPeriodDao.findAll(pageable);
		else
			return examPeriodDao.findByNameContaining(pageable, search);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(examPeriodDao.existsById(id))
			examPeriodDao.deleteById(id);
		else
			throw new EntityNotFoundException("Exam period with that ID does not exists!");
	}

	@Override
	public ExamPeriodEntity save(ExamPeriodEntity examPeriodEntity) throws EntityExistsException {
		Optional<ExamPeriodEntity> entity = examPeriodDao.findByName(examPeriodEntity.getName());
		if (entity.isPresent()) {
			throw new EntityExistsException("Exam period already exists!");
		}
		ExamPeriodEntity e = examPeriodDao.save(examPeriodEntity);
		return e;
	}
	
	@Override
	public ExamPeriodEntity update(ExamPeriodEntity examPeriodEntity) throws EntityNotFoundException {
		Optional<ExamPeriodEntity> entity = examPeriodDao.findByName(examPeriodEntity.getName());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Exam period does not exist!");
		}
		ExamPeriodEntity e = examPeriodDao.save(examPeriodEntity);
		return e;
		
	}
	
	@Override
	public Optional<ExamPeriodEntity> findById(Long id) {
		Optional<ExamPeriodEntity> exam = examPeriodDao.findById(id);
		if (exam.isPresent()) {
			return Optional.of(exam.get());
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<ExamPeriodEntity> findByName(String name) {
		Optional<ExamPeriodEntity> exam = examPeriodDao.findByName(name);
		if (exam.isPresent()) {
			return Optional.of(exam.get());
		}
		return Optional.empty();
	}
}
