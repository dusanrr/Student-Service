package student.service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.dto.CityDto;
import student.service.dto.TitleDto;
import student.service.exception.EntityNotPresent;
import student.service.exception.ExistEntityException;

public interface TitleService {

	List<TitleDto> findAll();

	void deleteById(Long id);

	TitleDto save(TitleDto titleDto) throws ExistEntityException;
	
	TitleDto update(TitleDto titleDto) throws EntityNotPresent ;

	Optional<TitleDto> findById(Long id);

	Page<TitleDto> findByPage(Pageable pageable);
}