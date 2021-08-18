package student.service.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import student.service.entity.ExamPeriodEntity;

public interface ExamPeriodDao extends PagingAndSortingRepository<ExamPeriodEntity, Long> {

	Optional<ExamPeriodEntity> findById(Long id);
	
	Optional<ExamPeriodEntity> findByName(String name);
	
	Page<ExamPeriodEntity> findByNameContaining(Pageable pageable, String search);
}
