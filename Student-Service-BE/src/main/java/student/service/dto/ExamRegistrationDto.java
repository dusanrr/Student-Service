package student.service.dto;

import java.io.Serializable;
import java.util.Date;

public class ExamRegistrationDto implements Serializable {

	private static final long serialVersionUID = -29541063926359547L;
	
	private Long id;
	
	private StudentDto studentDto;
	
	private ExamDto examDto;
	
	private Date examRegistrationTime;
	
	public ExamRegistrationDto() {
		
	}

	public ExamRegistrationDto(Long id, StudentDto studentDto, ExamDto examDto, Date examRegistrationTime) {
		super();
		this.id = id;
		this.studentDto = studentDto;
		this.examDto = examDto;
		this.examRegistrationTime = examRegistrationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentDto getStudentDto() {
		return studentDto;
	}

	public void setStudentDto(StudentDto studentDto) {
		this.studentDto = studentDto;
	}

	public ExamDto getExamDto() {
		return examDto;
	}

	public void setExamDto(ExamDto examDto) {
		this.examDto = examDto;
	}

	public Date getExamRegistrationTime() {
		return examRegistrationTime;
	}

	public void setExamRegistrationTime(Date examRegistrationTime) {
		this.examRegistrationTime = examRegistrationTime;
	}

	@Override
	public String toString() {
		return "ExamRegistrationDto [id=" + id + ", studentDto=" + studentDto + ", examDto=" + examDto
				+ ", examRegistrationTime=" + examRegistrationTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examDto == null) ? 0 : examDto.hashCode());
		result = prime * result + ((examRegistrationTime == null) ? 0 : examRegistrationTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((studentDto == null) ? 0 : studentDto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExamRegistrationDto other = (ExamRegistrationDto) obj;
		if (examDto == null) {
			if (other.examDto != null)
				return false;
		} else if (!examDto.equals(other.examDto))
			return false;
		if (examRegistrationTime == null) {
			if (other.examRegistrationTime != null)
				return false;
		} else if (!examRegistrationTime.equals(other.examRegistrationTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (studentDto == null) {
			if (other.studentDto != null)
				return false;
		} else if (!studentDto.equals(other.studentDto))
			return false;
		return true;
	}

}
