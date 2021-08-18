package student.service.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import student.service.entity.TitleEntity;

public interface TitleService {

	List<TitleEntity> findAll();

	void deleteById(Long id) throws EntityNotFoundException;

	TitleEntity save(TitleEntity titleEntity) throws EntityExistsException;
	
	TitleEntity update(TitleEntity titleEntity) throws EntityNotFoundException ;

	Optional<TitleEntity> findById(Long id);

	Page<TitleEntity> findByPage(Pageable pageable);
}
