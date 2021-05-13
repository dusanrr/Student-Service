package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import student.service.entity.ExamEntity;

public interface ExamDao extends PagingAndSortingRepository<ExamEntity, Long> {
	
	Optional<ExamEntity> findById(Long id);

}