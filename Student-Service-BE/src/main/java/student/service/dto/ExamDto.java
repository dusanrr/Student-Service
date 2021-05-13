package student.service.dto;

import java.io.Serializable;
import java.util.Date;

public class ExamDto implements Serializable {

	private static final long serialVersionUID = -8455658057792275287L;

	private Long id;
	
	private SubjectDto subjectDto;
	
	private ProfessorDto professorDto;
	
	private Date examDate;
	
	private ExamPeriodDto examPeriodDto;
	
	public ExamDto() {
		
	}

	public ExamDto(Long id, SubjectDto subjectDto, ProfessorDto professorDto, Date examDate,
			ExamPeriodDto examPeriodDto) {
		super();
		this.id = id;
		this.subjectDto = subjectDto;
		this.professorDto = professorDto;
		this.examDate = examDate;
		this.examPeriodDto = examPeriodDto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SubjectDto getSubjectDto() {
		return subjectDto;
	}

	public void setSubjectDto(SubjectDto subjectDto) {
		this.subjectDto = subjectDto;
	}

	public ProfessorDto getProfessorDto() {
		return professorDto;
	}

	public void setProfessorDto(ProfessorDto professorDto) {
		this.professorDto = professorDto;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public ExamPeriodDto getExamPeriodDto() {
		return examPeriodDto;
	}

	public void setExamPeriodDto(ExamPeriodDto examPeriodDto) {
		this.examPeriodDto = examPeriodDto;
	}

	@Override
	public String toString() {
		return "ExamDto [id=" + id + ", subjectDto=" + subjectDto + ", professorDto=" + professorDto + ", examDate="
				+ examDate + ", examPeriodDto=" + examPeriodDto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examDate == null) ? 0 : examDate.hashCode());
		result = prime * result + ((examPeriodDto == null) ? 0 : examPeriodDto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((professorDto == null) ? 0 : professorDto.hashCode());
		result = prime * result + ((subjectDto == null) ? 0 : subjectDto.hashCode());
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
		ExamDto other = (ExamDto) obj;
		if (examDate == null) {
			if (other.examDate != null)
				return false;
		} else if (!examDate.equals(other.examDate))
			return false;
		if (examPeriodDto == null) {
			if (other.examPeriodDto != null)
				return false;
		} else if (!examPeriodDto.equals(other.examPeriodDto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (professorDto == null) {
			if (other.professorDto != null)
				return false;
		} else if (!professorDto.equals(other.professorDto))
			return false;
		if (subjectDto == null) {
			if (other.subjectDto != null)
				return false;
		} else if (!subjectDto.equals(other.subjectDto))
			return false;
		return true;
	}

}
