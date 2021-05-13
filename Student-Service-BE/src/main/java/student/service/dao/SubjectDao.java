package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.SubjectEntity;


@Repository
public interface SubjectDao extends PagingAndSortingRepository<SubjectEntity, Long> {

	Optional<SubjectEntity> findByName(String name);

}
