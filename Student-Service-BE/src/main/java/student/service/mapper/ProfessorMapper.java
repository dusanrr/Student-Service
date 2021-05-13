package student.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.service.dto.ProfessorDto;
import student.service.entity.ProfessorEntity;


@Mapper
public interface ProfessorMapper {

	@Mapping(source = "city", target = "cityDto")
	@Mapping(source = "title", target = "titleDto")
	ProfessorDto toProfessorDto(ProfessorEntity professor);
	
	@Mapping(source = "cityDto" ,target="city")
	@Mapping(source = "titleDto" ,target="title")
	ProfessorEntity toProfessor(ProfessorDto professorDto);
	
}
