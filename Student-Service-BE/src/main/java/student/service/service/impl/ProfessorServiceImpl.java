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

import student.service.dao.ProfessorDao;
import student.service.entity.ProfessorEntity;
import student.service.service.ProfessorService;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

	private final ProfessorDao professorDao;

	@Autowired
	public ProfessorServiceImpl(ProfessorDao professorDao) {
		super();
		this.professorDao = professorDao;
	}

	@Override
	public List<ProfessorEntity> findAll() {
		List<ProfessorEntity> entites = (List<ProfessorEntity>)professorDao.findAll();
		return entites.stream().map(entity -> {
			return (entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ProfessorEntity> findByPage(Pageable pageable, String search) {
		if (search == null)
			return professorDao.findAll(pageable);
		else
			return professorDao.findByFirstNameAndLastNameContaining(pageable, search);
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(professorDao.existsById(id))
			professorDao.deleteById(id);
		else
			throw new EntityNotFoundException("Professor with that ID does not exists!");
	}

	@Override
	public ProfessorEntity save(ProfessorEntity professorEntity) throws EntityExistsException {
		Optional<ProfessorEntity> entity = professorDao.findByEmail(professorEntity.getEmail());
		if (entity.isPresent()) {
			throw new EntityExistsException("Professor already exists!");
		}
		ProfessorEntity p = professorDao.save(professorEntity);
		return p;
	}
	
	@Override
	public ProfessorEntity update(ProfessorEntity professorEntity) throws EntityNotFoundException {
		Optional<ProfessorEntity> entity = professorDao.findByEmail(professorEntity.getEmail());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Professor does not exist!");
		}
		ProfessorEntity p = professorDao.save(professorEntity);
		return p;
		
	}

	@Override
	public Optional<ProfessorEntity> findByEmail(String email) {
		Optional<ProfessorEntity> professor = professorDao.findByEmail(email);
		if (professor.isPresent()) {
			return Optional.of(professor.get());
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<ProfessorEntity> findById(Long id) {
		Optional<ProfessorEntity> professor = professorDao.findById(id);
		if (professor.isPresent()) {
			return Optional.of(professor.get());
		}
		return Optional.empty();
	}
}
