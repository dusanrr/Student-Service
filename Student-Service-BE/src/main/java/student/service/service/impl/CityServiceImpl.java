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

import student.service.dao.CityDao;
import student.service.dto.CityDto;
import student.service.dto.StudentDto;
import student.service.entity.CityEntity;
import student.service.entity.StudentEntity;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;
import student.service.mapper.CityMapper;
import student.service.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	private final CityDao cityDao;

	private final CityMapper cityMapper = Mappers.getMapper(CityMapper.class);

	@Autowired
	public CityServiceImpl(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public List<CityDto> findAll() {
		List<CityEntity> entites = (List)cityDao.findAll();
		return entites.stream().map(entity -> {
			return cityMapper.toCityDto(entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<CityDto> findByPage(Pageable pageable) {
		Page<CityDto> entites = cityDao.findAll(pageable).map(cityMapper::toCityDto);
		return entites;
	}

	@Override
	public void deleteById(Long id) {
		cityDao.deleteById(id);
	}

	@Override
	public CityDto save(CityDto cityDto) throws ExistEntityException {
		Optional<CityEntity> entity = cityDao.findById(cityDto.getNumber());
		if (entity.isPresent()) {
			throw new ExistEntityException(entity.get(), "City already exists!");
		}
		CityEntity c = cityDao.save(cityMapper.toCity(cityDto));
		return cityMapper.toCityDto(c);
	}
	
	@Override
	public CityDto update(CityDto cityDto) throws EntityNotPresent {
		Optional<CityEntity> entity = cityDao.findById(cityDto.getNumber());
		if (!entity.isPresent()) {
			throw new EntityNotPresent(cityDto.getNumber(), "City does not exist!");
		}
		CityEntity c = cityDao.save(cityMapper.toCity(cityDto));
		return cityMapper.toCityDto(c);
		
	}

	@Override
	public Optional<CityDto> findById(Long id) {
		Optional<CityEntity> city = cityDao.findById(id);
		if (city.isPresent()) {
			return Optional.of(cityMapper.toCityDto(city.get()));
		}
		return Optional.empty();
	}

}