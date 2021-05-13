package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.CityDto;
import student.service.dto.StudentDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface CityService {
	
	List<CityDto> findAll();

	void deleteById(Long id);

	CityDto save(CityDto cityDto) throws ExistEntityException;
	
	CityDto update(CityDto cityDto) throws EntityNotPresent ;

	Optional<CityDto> findById(Long id);

	Page<CityDto> findByPage(Pageable pageable);
}
