package student.service.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.RoleEntity;
import student.service.enums.ERole;


@Repository
public interface RoleDao extends JpaRepository<RoleEntity, Long> {
	Optional<RoleEntity> findByName(ERole name);
}
