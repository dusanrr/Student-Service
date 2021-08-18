package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.CityEntity;

public interface CityService {
	
	List<CityEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;;

	CityEntity save(CityEntity cityEntity) throws EntityExistsException;
	
	CityEntity update(CityEntity cityEntity) throws EntityNotFoundException ;

	Optional<CityEntity> findById(Long id);

	Page<CityEntity> findByPage(Pageable pageable);
}
