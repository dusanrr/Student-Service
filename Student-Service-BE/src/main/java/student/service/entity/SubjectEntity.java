package student.service.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subject")
public class SubjectEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name", nullable = false, length=30)
	@Size(min = 3, max = 30, message = "Minimal number of characters is 3...")
	@NotEmpty(message = "Name is required...")
	private String name;
	
	@Column(name="description", nullable = true, length=200)
	private String description;
	
	@Column(name="noOfESP", nullable = false, length=1)
	@NotNull(message = "No. of ESP is required...")
	private int noOfESP;
	
	@Column(name="yearOfStudy", nullable = true, length=1)
	private int yearOfStudy;
	
	@Column(name="semester", nullable = true, length=10)
	private String semester;
	
	@ManyToOne
	@JoinColumn(name = "professor_id")
	private ProfessorEntity professor;
	
	public SubjectEntity() {
		
	}

	public SubjectEntity(Long id, String name, String description, int noOfESP, int yearOfStudy, String semester,
			ProfessorEntity professor) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.noOfESP = noOfESP;
		this.yearOfStudy = yearOfStudy;
		this.semester = semester;
		this.professor = professor;
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

	public ProfessorEntity getProfessor() {
		return professor;
	}

	public void setProfessor(ProfessorEntity professor) {
		this.professor = professor;
	}

	@Override
	public String toString() {
		return "SubjectEntity [id=" + id + ", name=" + name + ", description=" + description + ", noOfESP=" + noOfESP
				+ ", yearOfStudy=" + yearOfStudy + ", semester=" + semester + ", professor=" + professor + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + noOfESP;
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
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
		SubjectEntity other = (SubjectEntity) obj;
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
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
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