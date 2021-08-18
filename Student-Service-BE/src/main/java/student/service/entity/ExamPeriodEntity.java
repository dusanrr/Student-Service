package student.service.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exam_period")
public class ExamPeriodEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private Date examPeriodFrom;
	
	private Date examPeriodTo;
	
	public ExamPeriodEntity() {
		
	}

	public ExamPeriodEntity(Long id, String name, Date examPeriodFrom, Date examPeriodTo) {
		super();
		this.id = id;
		this.name = name;
		this.examPeriodFrom = examPeriodFrom;
		this.examPeriodTo = examPeriodTo;
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

	public Date getExamPeriodFrom() {
		return examPeriodFrom;
	}

	public void setExamPeriodFrom(Date examPeriodFrom) {
		this.examPeriodFrom = examPeriodFrom;
	}

	public Date getExamPeriodTo() {
		return examPeriodTo;
	}

	public void setExamPeriodTo(Date examPeriodTo) {
		this.examPeriodTo = examPeriodTo;
	}

	@Override
	public String toString() {
		return "ExamPeriod [id=" + id + ", name=" + name + ", examPeriodFrom=" + examPeriodFrom + ", examPeriodTo="
				+ examPeriodTo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((examPeriodFrom == null) ? 0 : examPeriodFrom.hashCode());
		result = prime * result + ((examPeriodTo == null) ? 0 : examPeriodTo.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ExamPeriodEntity other = (ExamPeriodEntity) obj;
		if (examPeriodFrom == null) {
			if (other.examPeriodFrom != null)
				return false;
		} else if (!examPeriodFrom.equals(other.examPeriodFrom))
			return false;
		if (examPeriodTo == null) {
			if (other.examPeriodTo != null)
				return false;
		} else if (!examPeriodTo.equals(other.examPeriodTo))
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
		return true;
	}

}
