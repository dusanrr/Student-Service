package student.service.mapper;

import org.mapstruct.Mapper;

import student.service.dto.ExamPeriodDto;
import student.service.entity.ExamPeriodEntity;

@Mapper
public interface ExamPeriodMapper {
	ExamPeriodDto toExamPeriodDto(ExamPeriodEntity examPeriod);

	ExamPeriodEntity toExamPeriod(ExamPeriodDto examPeriodDto);
}
