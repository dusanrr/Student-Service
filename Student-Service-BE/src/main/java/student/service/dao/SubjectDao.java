package student.service.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.SubjectEntity;


@Repository
public interface SubjectDao extends PagingAndSortingRepository<SubjectEntity, Long> {

	Optional<SubjectEntity> findById(Long id);
	
	Optional<SubjectEntity> findByName(String name);
	
	Page<SubjectEntity> findAll(Pageable pageable);
	
	Page<SubjectEntity> findByNameContaining(Pageable pageable, String search);

}
