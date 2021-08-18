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

import student.service.dao.SubjectDao;
import student.service.entity.SubjectEntity;
import student.service.logging.Loggable;
import student.service.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	private final SubjectDao subjectDao;

	@Autowired
	public SubjectServiceImpl(SubjectDao subjectDao) {
		super();
		this.subjectDao = subjectDao;
	}

	@Loggable
	@Override
	public List<SubjectEntity> findAll() {
		List<SubjectEntity> entites = (List<SubjectEntity>)subjectDao.findAll();
		return entites.stream().map(entity -> {
			return entity;
		}).collect(Collectors.toList());
	}
	
	@Loggable
	@Override
	public Page<SubjectEntity> findByPage(Pageable pageable, String search) {
		if (search == null)
			return subjectDao.findAll(pageable);
		else
			return subjectDao.findByNameContaining(pageable, search);
	}

	@Loggable
	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(subjectDao.existsById(id))
			subjectDao.deleteById(id);
		else
			throw new EntityNotFoundException("Subject with that ID does not exists!");
	}

	@Loggable
	@Override
	public SubjectEntity save(SubjectEntity subjectEntity) throws EntityExistsException {
		Optional<SubjectEntity> entity = subjectDao.findByName(subjectEntity.getName());
		if (entity.isPresent()) {
			throw new EntityExistsException("Subject already exists!");
		}
		SubjectEntity s = subjectDao.save(subjectEntity);
		return s;
		//throw new EntityExistsException("Subject already exists!");
	}
	
	@Loggable
	@Override
	public SubjectEntity update(SubjectEntity subjectEntity) throws EntityNotFoundException {
		Optional<SubjectEntity> entity = subjectDao.findById(subjectEntity.getId());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Subject does not exist!");
		}
		SubjectEntity s = subjectDao.save(subjectEntity);
		return s;
		//throw new EntityNotFoundException("Subject does not exist!");
	}
	
	@Loggable
	@Override
	public Optional<SubjectEntity> findById(Long id) {
		Optional<SubjectEntity> subject = subjectDao.findById(id);
		if (subject.isPresent()) {
			return Optional.of(subject.get());
		}
		return Optional.empty();
	}

	@Override
	public Optional<SubjectEntity> findByName(String name) {
		Optional<SubjectEntity> subject = subjectDao.findByName(name);
		if (subject.isPresent()) {
			return Optional.of(subject.get());
		}
		return Optional.empty();
	}
}
