package student.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.service.dto.ExamDto;
import student.service.entity.ExamEntity;

@Mapper
public interface ExamMapper {
	
	@Mapping(source = "subject", target = "subjectDto")
	@Mapping(source = "professor", target = "professorDto")
	@Mapping(source = "examPeriod", target = "examPeriodDto")
	ExamDto toExamDto(ExamEntity exam);

	@Mapping(source = "subjectDto", target = "subject")
	@Mapping(source = "professorDto", target = "professor")
	@Mapping(source = "examPeriodDto", target = "examPeriod")
	ExamEntity toExam(ExamDto examDto);
}
