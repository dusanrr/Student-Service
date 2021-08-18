package student.service.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import student.service.entity.ExamRegistrationEntity;

public interface ExamRegistrationDao extends PagingAndSortingRepository<ExamRegistrationEntity, Long> {

	Optional<ExamRegistrationEntity> findById(Long id);
	
	@Query("select e from ExamRegistrationEntity e where e.student.indexNumber=?1 and e.exam.id=?2")
	Optional<ExamRegistrationEntity> findByStudentAndExam(String indexNumber, Long id);
	
	@Query("select er from ExamRegistrationEntity er where er.student.indexNumber LIKE %?1%")
	Page<ExamRegistrationEntity> findByStudentIndexNumberContaining(Pageable pageable, String search);
	
}