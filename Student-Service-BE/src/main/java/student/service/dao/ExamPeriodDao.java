package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import student.service.entity.ExamPeriodEntity;

public interface ExamPeriodDao extends PagingAndSortingRepository<ExamPeriodEntity, Long> {

	Optional<ExamPeriodEntity> findById(Long id);
	
	Optional<ExamPeriodEntity> findByName(String name);
}