package student.service.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "title")
public class TitleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "ID is required...")
	private Long id;
	
	@Size(min = 3, max = 10, message = "Betweeen 3 and 10...")
	@NotEmpty(message = "Title name is required...")
	private String titleName;

	public TitleEntity() {
	}

	public TitleEntity(Long id, String titleName) {
		this.id = id;
		this.titleName = titleName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setName(String titleName) {
		this.titleName = titleName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((titleName == null) ? 0 : titleName.hashCode());
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
		TitleEntity other = (TitleEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (titleName == null) {
			if (other.titleName != null)
				return false;
		} else if (!titleName.equals(other.titleName))
			return false;
		return true;
	}
	
}
