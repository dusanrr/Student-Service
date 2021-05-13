package student.service.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import student.service.entity.CityEntity;
import student.service.entity.ProfessorEntity;
import student.service.entity.TitleEntity;

public class ProfessorDto implements Serializable {

	private static final long serialVersionUID = -5227943210571304153L;
	
	private Long id;
	
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
	
	@Size(min = 6, max = 15, message = "Minimal number of characters is 3...")
	private String phone;
	
	@NotNull(message = "Reelection date is required...")
	private Date reelectionDate;
	
	private TitleDto titleDto;
	
	
	public ProfessorDto() {
		
	}

	public ProfessorDto(String firstName, String lastName, String email, String adress, CityDto cityDto,
			String phone, Date reelectionDate, TitleDto titleDto) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.adress = adress;
		this.cityDto = cityDto;
		this.phone = phone;
		this.reelectionDate = reelectionDate;
		this.titleDto = titleDto;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
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


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getReelectionDate() {
		return reelectionDate;
	}


	public void setReelectionDate(Date reelectionDate) {
		this.reelectionDate = reelectionDate;
	}


	public TitleDto getTitleDto() {
		return titleDto;
	}


	public void setTitleDto(TitleDto titleDto) {
		this.titleDto = titleDto;
	}
	

	@Override
	public String toString() {
		return "ProfessorDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", adress=" + adress + ", cityDto=" + cityDto + ", phone=" + phone + ", reelectionDate="
				+ reelectionDate + ", titleDto=" + titleDto + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + ((cityDto == null) ? 0 : cityDto.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((reelectionDate == null) ? 0 : reelectionDate.hashCode());
		result = prime * result + ((titleDto == null) ? 0 : titleDto.hashCode());
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
		ProfessorDto other = (ProfessorDto) obj;
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
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (reelectionDate == null) {
			if (other.reelectionDate != null)
				return false;
		} else if (!reelectionDate.equals(other.reelectionDate))
			return false;
		if (titleDto == null) {
			if (other.titleDto != null)
				return false;
		} else if (!titleDto.equals(other.titleDto))
			return false;
		return true;
	}
}
