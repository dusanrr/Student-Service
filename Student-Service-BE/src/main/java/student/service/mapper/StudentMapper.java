package student.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import student.service.dto.StudentDto;
import student.service.entity.StudentEntity;

@Mapper
public interface StudentMapper {

	@Mapping(source = "city", target = "cityDto")
	
	StudentDto toStudentDto(StudentEntity student);
	
	@Mapping(source = "cityDto" ,target="city")
	StudentEntity toStudent(StudentDto studentDto);
	
}