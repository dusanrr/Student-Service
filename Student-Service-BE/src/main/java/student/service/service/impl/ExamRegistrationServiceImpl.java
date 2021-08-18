package student.service.service.impl;

import java.sql.Date;
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

import student.service.dao.ExamRegistrationDao;
import student.service.entity.ExamRegistrationEntity;
import student.service.service.ExamRegistrationService;

@Service
@Transactional
public class ExamRegistrationServiceImpl implements ExamRegistrationService {

	private final ExamRegistrationDao examRegistrationDao;

	@Autowired
	public ExamRegistrationServiceImpl(ExamRegistrationDao examRegistrationDao) {
		super();
		this.examRegistrationDao = examRegistrationDao;
	}

	@Override
	public List<ExamRegistrationEntity> findAll() {
		List<ExamRegistrationEntity> entites = (List<ExamRegistrationEntity>)examRegistrationDao.findAll();
		return entites.stream().map(entity -> {
			return (entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ExamRegistrationEntity> findByPage(Pageable pageable, String search) {
		if (search == null)
			return examRegistrationDao.findAll(pageable);
		else
			return examRegistrationDao.findByStudentIndexNumberContaining(pageable, search);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(examRegistrationDao.existsById(id))
			examRegistrationDao.deleteById(id);
		else
			throw new EntityNotFoundException("Exam registration with that ID does not exists!");
	}

	@Override
	public ExamRegistrationEntity save(ExamRegistrationEntity examRegistrationEntity) throws EntityExistsException {
		Optional<ExamRegistrationEntity> entity = examRegistrationDao.findByStudentAndExam(examRegistrationEntity.getStudent().getIndexNumber(), examRegistrationEntity.getExam().getId());
		if (entity.isPresent()) {
			throw new EntityExistsException("You already registered for that exam!");
		}
		
		long millis=System.currentTimeMillis();  
        Date date = new Date(millis);      
		examRegistrationEntity.setExamRegistrationTime(date);
		ExamRegistrationEntity e = examRegistrationDao.save(examRegistrationEntity);
		return e;
	}
	
	@Override
	public ExamRegistrationEntity update(ExamRegistrationEntity examRegistrationEntity) throws EntityNotFoundException {
		Optional<ExamRegistrationEntity> entity = examRegistrationDao.findById(examRegistrationEntity.getId());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Exam registration does not exist!");
		}
		ExamRegistrationEntity e = examRegistrationDao.save(examRegistrationEntity);
		return e;
		
	}
	
	@Override
	public Optional<ExamRegistrationEntity> findById(Long id) {
		Optional<ExamRegistrationEntity> examRegistrationEntity = examRegistrationDao.findById(id);
		if (examRegistrationEntity.isPresent()) {
			return Optional.of(examRegistrationEntity.get());
		}
		return Optional.empty();
	}
}
