package student.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.service.dto.ExamRegistrationDto;
import student.service.entity.ExamRegistrationEntity;

@Mapper
public interface ExamRegistrationMapper {
	
	@Mapping(source = "student", target = "studentDto")
	@Mapping(source = "exam", target = "examDto")
	ExamRegistrationDto toExamRegistrationDto(ExamRegistrationEntity examRegistration);

	@Mapping(source = "studentDto", target = "student")
	@Mapping(source = "examDto", target = "exam")
	ExamRegistrationEntity toExamRegistration(ExamRegistrationDto examRegistrationDto);
}
