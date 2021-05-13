package student.service.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import student.service.entity.CityEntity;

public class StudentDto implements Serializable {

	private static final long serialVersionUID = 4392069690903739486L;
	
	@Size(min = 4, max = 4, message = "Exact number of characters is 4...")
	@NotEmpty(message = "Index number is required...")
	private String indexNumber;
	
	@NotEmpty(message = "Index year is required...")
	private String indexYear;
	
	@Size(min = 3, max = 30, message = "Minimal number of characters is 3...")
	@NotEmpty(message = "First name is required...")
	private String firstName;
	
	@Size(min = 3, max = 30, message = "Minimal number of characters is 3...")
	@NotEmpty(message = "Last name is required...")
	private String lastName;
	
	@Email(message = "Email should be valid")
	private String email;
	
	@Size(min = 3, max = 50, message = "Minimal number of characters is 3...")
	private String adress;
	
	private CityDto cityDto;
	
	private int currentYearOfStudy;
	
	public StudentDto() {
		
	}
	
	public StudentDto(String indexNumber, String indexYear, String firstName, String lastName, String email,
			String adress, CityDto cityDto, int currentYearOfStudy) {
		this.indexNumber = indexNumber;
		this.indexYear = indexYear;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.cityDto = cityDto;
		this.currentYearOfStudy = currentYearOfStudy;
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

	public CityDto getCityDto() {
		return cityDto;
	}

	public void setCityDto(CityDto cityDto) {
		this.cityDto = cityDto;
	}

	public int getCurrentYearOfStudy() {
		return currentYearOfStudy;
	}

	public void setCurrentYearOfStudy(int currentYearOfStudy) {
		this.currentYearOfStudy = currentYearOfStudy;
	}

	@Override
	public String toString() {
		return "StudentDto [indexNumber=" + indexNumber + ", indexYear=" + indexYear + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", adress=" + adress + ", cityDto=" + cityDto
				+ ", currentYearOfStudy=" + currentYearOfStudy + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((cityDto == null) ? 0 : cityDto.hashCode());
		result = prime * result + currentYearOfStudy;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
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
		StudentDto other = (StudentDto) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (cityDto == null) {
			if (other.cityDto != null)
				return false;
		} else if (!cityDto.equals(other.cityDto))
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
