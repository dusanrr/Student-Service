package student.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.service.dto.SubjectDto;
import student.service.entity.SubjectEntity;

@Mapper
public interface SubjectMapper {

	@Mapping(source = "professor", target = "professorDto")
	SubjectDto toSubjectDto(SubjectEntity subject);
	
	@Mapping(source = "professorDto", target = "professor")
	SubjectEntity toSubject(SubjectDto subjectDto);
	
}
