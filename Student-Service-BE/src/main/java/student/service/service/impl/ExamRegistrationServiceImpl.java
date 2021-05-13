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

import student.service.dao.ExamRegistrationDao;
import student.service.dto.ExamRegistrationDto;
import student.service.entity.ExamRegistrationEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.ExamRegistrationMapper;
import student.service.service.ExamRegistrationService;

@Service
@Transactional
public class ExamRegistrationServiceImpl implements ExamRegistrationService {

	private final ExamRegistrationDao examRegistrationDao;

	private final ExamRegistrationMapper examRegistrationMapper = Mappers.getMapper(ExamRegistrationMapper.class);

	@Autowired
	public ExamRegistrationServiceImpl(ExamRegistrationDao examRegistrationDao) {
		super();
		this.examRegistrationDao = examRegistrationDao;
	}

	
	@Override
	public List<ExamRegistrationDto> findAll() {
		List<ExamRegistrationEntity> entites = (List)examRegistrationDao.findAll();
		return entites.stream().map(entity -> {
			return examRegistrationMapper.toExamRegistrationDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ExamRegistrationDto> findByPage(Pageable pageable) {
		Page<ExamRegistrationDto> entites = examRegistrationDao.findAll(pageable).map(examRegistrationMapper::toExamRegistrationDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		examRegistrationDao.deleteById(id);
	}

	@Override
	public ExamRegistrationDto save(ExamRegistrationDto examDto) throws ExistEntityException {
		Optional<ExamRegistrationEntity> entity = examRegistrationDao.findById(examDto.getId());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Exam registration already exists!");
		}
		ExamRegistrationEntity e = examRegistrationDao.save(examRegistrationMapper.toExamRegistration(examDto));
		return examRegistrationMapper.toExamRegistrationDto(e);
	}
	
	@Override
	public ExamRegistrationDto update(ExamRegistrationDto examDto) throws EntityNotPresent {
		Optional<ExamRegistrationEntity> entity = examRegistrationDao.findById(examDto.getId());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(examDto.getId(), "Exam registration does not exist!");
		}
		ExamRegistrationEntity e = examRegistrationDao.save(examRegistrationMapper.toExamRegistration(examDto));
		return examRegistrationMapper.toExamRegistrationDto(e);
		
	}
	
	@Override
	public Optional<ExamRegistrationDto> findById(Long id) {
		Optional<ExamRegistrationEntity> exam = examRegistrationDao.findById(id);
		if (exam.isPresent()) {
			return Optional.of(examRegistrationMapper.toExamRegistrationDto(exam.get()));
		}
		return Optional.empty();
	}
}
