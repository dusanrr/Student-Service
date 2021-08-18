package student.service.dao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.ProfessorEntity;
import student.service.entity.SubjectEntity;


@Repository
public interface ProfessorDao extends PagingAndSortingRepository<ProfessorEntity, Long>{
	Optional<ProfessorEntity> findByEmail(String email);
	Optional<ProfessorEntity> findById(Long id);
	
	@Query("select p from ProfessorEntity p where CONCAT(p.firstName, ' ', p.lastName)  LIKE %?1%")
	Page<ProfessorEntity> findByFirstNameAndLastNameContaining(Pageable pageable, String search);
}

