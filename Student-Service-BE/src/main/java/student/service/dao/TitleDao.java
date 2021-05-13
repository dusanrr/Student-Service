package student.service.dao;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import student.service.entity.TitleEntity;

@Repository
public interface TitleDao extends PagingAndSortingRepository<TitleEntity, Long> {

	Optional<TitleEntity> findByTitleName(String titleName);

}
