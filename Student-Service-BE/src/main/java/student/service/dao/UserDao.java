package student.service.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.UserEntity;

@Repository
public interface UserDao extends JpaRepository<UserEntity, Long> {
	Optional<UserEntity> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
