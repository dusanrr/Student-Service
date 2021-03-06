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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student", uniqueConstraints = { @UniqueConstraint(columnNames = {"indexNumber", "indexYear"})})
public class StudentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="indexNumber", nullable = false, length=4)
	@Size(min = 4, max = 4, message = "Exact number of characters is 4...")
	@NotEmpty(message = "Index number is required...")
	private String indexNumber;
	
	@Column(name="indexYear", nullable = false, length=4)
	@NotEmpty(message = "Index year is required...")
	private String indexYear;
	
	@Column(name="firstName", nullable = false, length=30)
	@Size(min = 3, max = 30, message = "Minimal number of characters is 3...")
	@NotEmpty(message = "First name is required...")
	private String firstName;
	
	@Column(name="lastName", nullable = false, length=30)
	@Size(min = 3, max = 30, message = "Minimal number of characters is 3...")
	@NotEmpty(message = "Last name is required...")
	private String lastName;
	
	@Column(name="email", nullable = true, length=30, unique = true)
	@Email(message = "Email should be valid")
	private String email;
	
	@Column(name="adress", nullable = true, length=50)
	@Size(min = 3, max = 50, message = "Minimal number of characters is 3...")
	private String adress;
	
	@ManyToOne
	@JoinColumn(name = "city_number")
	private CityEntity city;
	
	@Column(name="currentYearOfStudy", nullable = false, length=5)
	private int currentYearOfStudy;
	
	
	public StudentEntity() {
		
	}
	
	public StudentEntity(Long id, String indexNumber, String indexYear, String firstName, String lastName, String email,
			String adress, int postalCode, int currentYearOfStudy, CityEntity city) {
		super();
		this.id = id;
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.city = city;
		this.currentYearOfStudy = currentYearOfStudy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIndexNumber() {
		return indexNumber;
	}
	public void setIndexNumber(String indexNumber) {
		this.indexNumber = indexNumber;
	}
	public String getIndexYear() {
		return indexYear;
	}
	public void setIndexYear(String indexYear) {
		this.indexYear = indexYear;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public CityEntity getCity() {
		return city;
	}
	public void setCity(CityEntity city) {
		this.city = city;
	}
	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}
	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	@Override
	public String toString() {
		return "StudentEntity [id=" + id + ", indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", adress=" + adress + ", city=" + city
				+ ", currentYearOfStudy=" + currentYearOfStudy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + currentYearOfStudy;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((indexNumber == null) ? 0 : indexNumber.hashCode());
		result = prime * result + ((indexYear == null) ? 0 : indexYear.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		StudentEntity other = (StudentEntity) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (currentYearOfStudy != other.currentYearOfStudy)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (indexNumber == null) {
			if (other.indexNumber != null)
				return false;
		} else if (!indexNumber.equals(other.indexNumber))
			return false;
		if (indexYear == null) {
			if (other.indexYear != null)
				return false;
		} else if (!indexYear.equals(other.indexYear))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}
}
