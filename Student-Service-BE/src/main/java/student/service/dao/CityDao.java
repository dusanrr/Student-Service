package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.CityEntity;

@Repository
public interface CityDao extends PagingAndSortingRepository<CityEntity, Long> {

	Optional<CityEntity> findByName(String name);

}
