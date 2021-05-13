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

import student.service.dao.ExamDao;
import student.service.dto.ExamDto;
import student.service.entity.ExamEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.ExamMapper;
import student.service.mapper.ProfessorMapper;
import student.service.service.ExamService;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {

	private final ExamDao examDao;

	private final ExamMapper examMapper = Mappers.getMapper(ExamMapper.class);

	@Autowired
	public ExamServiceImpl(ExamDao examDao) {
		super();
		this.examDao = examDao;
	}

	
	@Override
	public List<ExamDto> findAll() {
		List<ExamEntity> entites = (List)examDao.findAll();
		return entites.stream().map(entity -> {
			return examMapper.toExamDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<ExamDto> findByPage(Pageable pageable) {
		Page<ExamDto> entites = examDao.findAll(pageable).map(examMapper::toExamDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		examDao.deleteById(id);
	}

	@Override
	public ExamDto save(ExamDto examDto) throws ExistEntityException {
		Optional<ExamEntity> entity = examDao.findById(examDto.getSubjectDto().getId());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Exam already exists!");
		}
		ExamEntity e = examDao.save(examMapper.toExam(examDto));
		return examMapper.toExamDto(e);
	}
	
	@Override
	public ExamDto update(ExamDto examDto) throws EntityNotPresent {
		Optional<ExamEntity> entity = examDao.findById(examDto.getId());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(examDto.getId(), "Exam does not exist!");
		}
		ExamEntity e = examDao.save(examMapper.toExam(examDto));
		return examMapper.toExamDto(e);
		
	}
	
	@Override
	public Optional<ExamDto> findById(Long id) {
		Optional<ExamEntity> exam = examDao.findById(id);
		if (exam.isPresent()) {
			return Optional.of(examMapper.toExamDto(exam.get()));
		}
		return Optional.empty();
	}
}
