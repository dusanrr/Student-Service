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

import student.service.dao.ExamPeriodDao;
import student.service.dto.ExamPeriodDto;
import student.service.entity.ExamPeriodEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.ExamPeriodMapper;
import student.service.service.ExamPeriodService;

@Service
@Transactional
public class ExamPeriodServiceImpl implements ExamPeriodService {

	private final ExamPeriodDao examPeriodDao;

	private final ExamPeriodMapper examPeriodMapper = Mappers.getMapper(ExamPeriodMapper.class);

	@Autowired
	public ExamPeriodServiceImpl(ExamPeriodDao examPeriodDao) {
		super();
		this.examPeriodDao = examPeriodDao;
	}

	
	@Override
	public List<ExamPeriodDto> findAll() {
		List<ExamPeriodEntity> entites = (List)examPeriodDao.findAll();
		return entites.stream().map(entity -> {
			return examPeriodMapper.toExamPeriodDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ExamPeriodDto> findByPage(Pageable pageable) {
		Page<ExamPeriodDto> entites = examPeriodDao.findAll(pageable).map(examPeriodMapper::toExamPeriodDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		examPeriodDao.deleteById(id);
	}

	@Override
	public ExamPeriodDto save(ExamPeriodDto examDto) throws ExistEntityException {
		Optional<ExamPeriodEntity> entity = examPeriodDao.findByName(examDto.getName());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Exam period already exists!");
		}
		ExamPeriodEntity e = examPeriodDao.save(examPeriodMapper.toExamPeriod(examDto));
		return examPeriodMapper.toExamPeriodDto(e);
	}
	
	@Override
	public ExamPeriodDto update(ExamPeriodDto examDto) throws EntityNotPresent {
		Optional<ExamPeriodEntity> entity = examPeriodDao.findByName(examDto.getName());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(examDto.getName(), "Exam period does not exist!");
		}
		ExamPeriodEntity e = examPeriodDao.save(examPeriodMapper.toExamPeriod(examDto));
		return examPeriodMapper.toExamPeriodDto(e);
		
	}
	
	@Override
	public Optional<ExamPeriodDto> findById(Long id) {
		Optional<ExamPeriodEntity> exam = examPeriodDao.findById(id);
		if (exam.isPresent()) {
			return Optional.of(examPeriodMapper.toExamPeriodDto(exam.get()));
		}
		return Optional.empty();
	}
	
	@Override
	public Optional<ExamPeriodDto> findByName(String name) {
		Optional<ExamPeriodEntity> exam = examPeriodDao.findByName(name);
		if (exam.isPresent()) {
			return Optional.of(examPeriodMapper.toExamPeriodDto(exam.get()));
		}
		return Optional.empty();
	}
}
