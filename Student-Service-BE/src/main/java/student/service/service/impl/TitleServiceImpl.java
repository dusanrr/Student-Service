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

import student.service.dao.TitleDao;
import student.service.entity.TitleEntity;
import student.service.service.TitleService;

@Service
@Transactional
public class TitleServiceImpl implements TitleService {

	private final TitleDao titleDao;

	@Autowired
	public TitleServiceImpl(TitleDao titleDao) {
		super();
		this.titleDao = titleDao;
	}

	@Override
	public List<TitleEntity> findAll() {
		List<TitleEntity> entites = (List<TitleEntity>)titleDao.findAll();
		return entites.stream().map(entity -> {
			return (entity);
		}).collect(Collectors.toList());
	}
	
	@Override
	public Page<TitleEntity> findByPage(Pageable pageable) {
		Page<TitleEntity> entites = titleDao.findAll(pageable);
		return entites;
	}

	@Override
	public void deleteById(Long id) throws EntityNotFoundException {
		if(titleDao.existsById(id))
			titleDao.deleteById(id);
		else
			throw new EntityNotFoundException("Title with that ID does not exists!");
	}

	@Override
	public TitleEntity save(TitleEntity titleEntity) throws EntityExistsException {
		Optional<TitleEntity> entity = titleDao.findById(titleEntity.getId());
		if (entity.isPresent()) {
			throw new EntityExistsException("Title already exists!");
		}
		TitleEntity t = titleDao.save(titleEntity);
		return t;
	}
	
	@Override
	public TitleEntity update(TitleEntity titleEntity) throws EntityNotFoundException {
		Optional<TitleEntity> entity = titleDao.findById(titleEntity.getId());
		if (!entity.isPresent()) {
			throw new EntityNotFoundException("Title does not exist!");
		}
		TitleEntity t = titleDao.save(titleEntity);
		return t;
	}

	@Override
	public Optional<TitleEntity> findById(Long id) {
		Optional<TitleEntity> title = titleDao.findById(id);
		if (title.isPresent()) {
			return Optional.of(title.get());
		}
		return Optional.empty();
	}
}
