package student.service.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import student.service.entity.ExamEntity;

public interface ExamDao extends PagingAndSortingRepository<ExamEntity, Long> {
	
	Optional<ExamEntity> findById(Long id);
	
	@Query("select e from ExamEntity e where e.subject.id=?1 and e.examDate >= CURRENT_DATE")
	Optional<ExamEntity> findExamWithSubjectAndDate(Long subject_id);
	
	@Query("select e from ExamEntity e"
			+ " inner join SubjectEntity s on s.id=e.subject.id"
			+ " inner join StudentEntity stud on stud.indexNumber=?1"
			+ " where s.yearOfStudy <= stud.currentYearOfStudy"
			+ " and e.examDate >= CURRENT_DATE"
			+ " and DATEDIFF(CURRENT_DATE, e.examDate) <= 7")
	Optional<List<?>> findAllPosibleStudentExams(String indexNumber);
	
	@Query("select e from ExamEntity e where e.subject.name LIKE %?1%")
	Page<ExamEntity> findBySubjectNameContaining(Pageable pageable, String search);

}
