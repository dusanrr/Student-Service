package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.StudentEntity;

@Repository
public interface StudentDao extends PagingAndSortingRepository<StudentEntity, String>{
	Optional<StudentEntity> findByIndexNumber(String indexNumber);
}
