package student.service.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import student.service.entity.ProfessorEntity;
import student.service.entity.SubjectEntity;

public class SubjectDto implements Serializable {

	private static final long serialVersionUID = -2720245110441727266L;
	
	private Long id;
	
	@Size(min = 3, max = 30, message = "Minimal number of characters is 3...")
	@NotEmpty(message = "Name is required...")
	private String name;
	
	private String description;
	
	@NotNull(message = "No. of ESP is required...")
	private int noOfESP;
	
	private int yearOfStudy;
	
	private String semester;
	
	private ProfessorDto professorDto;
	
	public SubjectDto() {
		
	}

	public SubjectDto(Long id, String name, String description, int noOfESP, int yearOfStudy, String semester,
			ProfessorDto professorDto) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.professorDto = professorDto;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public int getNoOfESP() {
		return noOfESP;
	}


	public void setNoOfESP(int noOfESP) {
		this.noOfESP = noOfESP;
	}


	public int getYearOfStudy() {
		return yearOfStudy;
	}


	public void setYearOfStudy(int yearOfStudy) {
		this.yearOfStudy = yearOfStudy;
	}


	public String getSemester() {
		return semester;
	}


	public void setSemester(String semester) {
		this.semester = semester;
	}


	public ProfessorDto getProfessorDto() {
		return professorDto;
	}


	public void setProfessorDto(ProfessorDto professorDto) {
		this.professorDto = professorDto;
	}


	@Override
	public String toString() {
		return "SubjectDto [id=" + id + ", name=" + name + ", description=" + description + ", noOfESP=" + noOfESP
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", professorDto=" + professorDto + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + noOfESP;
		result = prime * result + ((professorDto == null) ? 0 : professorDto.hashCode());
		result = prime * result + ((semester == null) ? 0 : semester.hashCode());
		result = prime * result + yearOfStudy;
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
		SubjectDto other = (SubjectDto) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (noOfESP != other.noOfESP)
			return false;
		if (professorDto == null) {
			if (other.professorDto != null)
				return false;
		} else if (!professorDto.equals(other.professorDto))
			return false;
		if (semester == null) {
			if (other.semester != null)
				return false;
		} else if (!semester.equals(other.semester))
			return false;
		if (yearOfStudy != other.yearOfStudy)
			return false;
		return true;
	}
	
}
