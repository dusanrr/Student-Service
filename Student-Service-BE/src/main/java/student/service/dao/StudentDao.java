package student.service.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.StudentEntity;

@Repository
public interface StudentDao extends PagingAndSortingRepository<StudentEntity, Long>{
	Optional<StudentEntity> findByIndexNumber(String indexNumber);

	boolean existsByIndexNumber(String indexNumber);

	void deleteByIndexNumber(String indexNumber);
	
	Page<StudentEntity> findByIndexNumberContaining(Pageable pageable, String search);
	
}

