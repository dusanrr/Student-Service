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

import student.service.dao.ProfessorDao;
import student.service.dto.ProfessorDto;
import student.service.dto.StudentDto;
import student.service.entity.ProfessorEntity;
import student.service.entity.StudentEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.ProfessorMapper;
import student.service.service.ProfessorService;

@Service
@Transactional
public class ProfessorServiceImpl implements ProfessorService {

	private final ProfessorDao professorDao;

	private final ProfessorMapper professorMapper = Mappers.getMapper(ProfessorMapper.class);

	@Autowired
	public ProfessorServiceImpl(ProfessorDao professorDao) {
		super();
		this.professorDao = professorDao;
	}

	
	@Override
	public List<ProfessorDto> findAll() {
		List<ProfessorEntity> entites = (List)professorDao.findAll();
		return entites.stream().map(entity -> {
			return professorMapper.toProfessorDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ProfessorDto> findByPage(Pageable pageable) {
		Page<ProfessorDto> entites = professorDao.findAll(pageable).map(professorMapper::toProfessorDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		professorDao.deleteById(id);
	}

	@Override
	public ProfessorDto save(ProfessorDto professorDto) throws ExistEntityException {
		Optional<ProfessorEntity> entity = professorDao.findByEmail(professorDto.getEmail());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Professor already exists!");
		}
		ProfessorEntity p = professorDao.save(professorMapper.toProfessor(professorDto));
		return professorMapper.toProfessorDto(p);
	}
	
	@Override
	public ProfessorDto update(ProfessorDto professorDto) throws EntityNotPresent {
		Optional<ProfessorEntity> entity = professorDao.findByEmail(professorDto.getEmail());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(professorDto.getEmail(), "Professor does not exist!");
		}
		ProfessorEntity p = professorDao.save(professorMapper.toProfessor(professorDto));
		return professorMapper.toProfessorDto(p);
		
	}

	@Override
	public Optional<ProfessorDto> findByEmail(String email) {
		Optional<ProfessorEntity> professor = professorDao.findByEmail(email);
		if (professor.isPresent()) {
			return Optional.of(professorMapper.toProfessorDto(professor.get()));
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<ProfessorDto> findById(Long id) {
		Optional<ProfessorEntity> professor = professorDao.findById(id);
		if (professor.isPresent()) {
			return Optional.of(professorMapper.toProfessorDto(professor.get()));
		}
		return Optional.empty();
	}
}
