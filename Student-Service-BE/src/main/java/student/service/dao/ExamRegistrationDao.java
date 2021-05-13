package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import student.service.entity.ExamRegistrationEntity;

public interface ExamRegistrationDao extends PagingAndSortingRepository<ExamRegistrationEntity, Long> {

	Optional<ExamRegistrationEntity> findById(Long id);
}
