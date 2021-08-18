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

import student.service.dao.CityDao;
import student.service.entity.CityEntity;
import student.service.service.CityService;

@Service
@Transactional
public class CityServiceImpl implements CityService {

	private final CityDao cityDao;

	@Autowired
	public CityServiceImpl(CityDao cityDao) {
		super();
		this.cityDao = cityDao;
	}

	@Override
	public List<CityEntity> findAll() {
		List<CityEntity> entites = (List<CityEntity>)cityDao.findAll();
		return entites.stream().map(entity -> {
			return entity;
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<CityEntity> findByPage(Pageable pageable) {
		Page<CityEntity> entites = cityDao.findAll(pageable);
		return entites;
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(cityDao.existsById(id))
			cityDao.deleteById(id);
		else
			throw new EntityNotFoundException("City with that ID does not exists!");
	}

	@Override
	public CityEntity save(CityEntity cityEntity) throws EntityExistsException {
		Optional<CityEntity> entity = cityDao.findById(cityEntity.getNumber());
		if (entity.isPresent()) {
			throw new EntityExistsException("City already exists!");
		}
		CityEntity c = cityDao.save(cityEntity);
		return c;
	}
	
	@Override
	public CityEntity update(CityEntity cityEntity) throws EntityNotFoundException {
		Optional<CityEntity> entity = cityDao.findById(cityEntity.getNumber());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("City does not exist!");
		}
		CityEntity c = cityDao.save(cityEntity);
		return c;
		
	}

	@Override
	public Optional<CityEntity> findById(Long id) {
		Optional<CityEntity> city = cityDao.findById(id);
		if (city.isPresent()) {
			return Optional.of(city.get());
		}
		return Optional.empty();
	}

}
