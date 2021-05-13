package student.service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;

@Entity
@Table(name = "exam_registration")
public class ExamRegistrationEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private StudentEntity student;
	
	@OneToOne
	@JoinColumn(name = "exam_id")
	private ExamEntity exam;
	
	private Date examRegistrationTime;
	
	public ExamRegistrationEntity() {
		
	}

	public ExamRegistrationEntity(Long id, StudentEntity student, ExamEntity exam, Date examRegistrationTime) {
		super();
		this.id = id;
		this.student = student;
		this.exam = exam;
		this.examRegistrationTime = examRegistrationTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StudentEntity getStudent() {
		return student;
	}

	public void setStudent(StudentEntity student) {
		this.student = student;
	}

	public ExamEntity getExam() {
		return exam;
	}

	public void setExam(ExamEntity exam) {
		this.exam = exam;
	}

	public Date getExamRegistrationTime() {
		return examRegistrationTime;
	}

	public void setExamRegistrationTime(Date examRegistrationTime) {
		this.examRegistrationTime = examRegistrationTime;
	}

	@Override
	public String toString() {
		return "ExamRegistrationEntity [id=" + id + ", student=" + student + ", exam=" + exam
				+ ", examRegistrationTime=" + examRegistrationTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exam == null) ? 0 : exam.hashCode());
		result = prime * result + ((examRegistrationTime == null) ? 0 : examRegistrationTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
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
		ExamRegistrationEntity other = (ExamRegistrationEntity) obj;
		if (exam == null) {
			if (other.exam != null)
				return false;
		} else if (!exam.equals(other.exam))
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
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}
	
}
