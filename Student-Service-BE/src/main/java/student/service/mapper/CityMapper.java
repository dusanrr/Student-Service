package student.service.mapper;

import org.mapstruct.Mapper;

import student.service.dto.CityDto;
import student.service.entity.CityEntity;

@Mapper
public interface CityMapper {
	CityDto toCityDto(CityEntity city);

	CityEntity toCity(CityDto cityDto);
}
