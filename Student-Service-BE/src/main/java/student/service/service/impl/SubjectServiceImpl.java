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

import student.service.dao.SubjectDao;
import student.service.dto.SubjectDto;
import student.service.entity.SubjectEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.SubjectMapper;
import student.service.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

	private final SubjectDao subjectDao;

	private final SubjectMapper subjectMapper = Mappers.getMapper(SubjectMapper.class);

	@Autowired
	public SubjectServiceImpl(SubjectDao subjectDao) {
		super();
		this.subjectDao = subjectDao;
	}

	
	@Override
	public List<SubjectDto> findAll() {
		List<SubjectEntity> entites = (List)subjectDao.findAll();
		return entites.stream().map(entity -> {
			return subjectMapper.toSubjectDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<SubjectDto> findByPage(Pageable pageable) {
		Page<SubjectDto> entites = subjectDao.findAll(pageable).map(subjectMapper::toSubjectDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		subjectDao.deleteById(id);;
	}

	@Override
	public SubjectDto save(SubjectDto subjectDto) throws ExistEntityException {
		Optional<SubjectEntity> entity = subjectDao.findByName(subjectDto.getName());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Subject already exists!");
		}
		SubjectEntity s = subjectDao.save(subjectMapper.toSubject(subjectDto));
		return subjectMapper.toSubjectDto(s);
	}
	
	@Override
	public SubjectDto update(SubjectDto subjectDto) throws EntityNotPresent {
		Optional<SubjectEntity> entity = subjectDao.findByName(subjectDto.getName());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(subjectDto.getName(), "Subject does not exist!");
		}
		SubjectEntity s = subjectDao.save(subjectMapper.toSubject(subjectDto));
		return subjectMapper.toSubjectDto(s);
		
	}

	@Override
	public Optional<SubjectDto> findById(Long id) {
		Optional<SubjectEntity> subject = subjectDao.findById(id);
		if (subject.isPresent()) {
			return Optional.of(subjectMapper.toSubjectDto(subject.get()));
		}
		return Optional.empty();
	}
}
