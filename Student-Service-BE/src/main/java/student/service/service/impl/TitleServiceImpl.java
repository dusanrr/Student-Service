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

import student.service.dao.TitleDao;
import student.service.dto.CityDto;
import student.service.dto.TitleDto;
import student.service.entity.TitleEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.TitleMapper;
import student.service.service.TitleService;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {

	private final TitleDao titleDao;

	private final TitleMapper titleMapper = Mappers.getMapper(TitleMapper.class);

	@Autowired
	public TitleServiceImpl(TitleDao titleDao) {
		super();
		this.titleDao = titleDao;
	}

	@Override
	public List<TitleDto> findAll() {
		List<TitleEntity> entites = (List)titleDao.findAll();
		return entites.stream().map(entity -> {
			return titleMapper.toTitleDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<TitleDto> findByPage(Pageable pageable) {
		Page<TitleDto> entites = titleDao.findAll(pageable).map(titleMapper::toTitleDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		titleDao.deleteById(id);
	}

	@Override
	public TitleDto save(TitleDto titleDto) throws ExistEntityException {
		Optional<TitleEntity> entity = titleDao.findById(titleDto.getId());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "Title already exists!");
		}
		TitleEntity t = titleDao.save(titleMapper.toTitle(titleDto));
		return titleMapper.toTitleDto(t);
	}
	
	@Override
	public TitleDto update(TitleDto titleDto) throws EntityNotPresent {
		Optional<TitleEntity> entity = titleDao.findById(titleDto.getId());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(titleDto.getId(), "Title does not exist!");
		}
		TitleEntity t = titleDao.save(titleMapper.toTitle(titleDto));
		return titleMapper.toTitleDto(t);
		
	}

	@Override
	public Optional<TitleDto> findById(Long id) {
		Optional<TitleEntity> title = titleDao.findById(id);
		if (title.isPresent()) {
			return Optional.of(titleMapper.toTitleDto(title.get()));
		}
		return Optional.empty();
	}
}
