package student.service.mapper;

import org.mapstruct.Mapper;

import student.service.dto.TitleDto;
import student.service.entity.TitleEntity;

@Mapper
public interface TitleMapper {
	TitleDto toTitleDto(TitleEntity title);

	TitleEntity toTitle(TitleDto titleDto);
}