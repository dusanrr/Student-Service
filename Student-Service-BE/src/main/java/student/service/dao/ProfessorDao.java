package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.ProfessorEntity;


@Repository
public interface ProfessorDao extends PagingAndSortingRepository<ProfessorEntity, Long>{
	Optional<ProfessorEntity> findByEmail(String email);
	Optional<ProfessorEntity> findById(Long id);
}
