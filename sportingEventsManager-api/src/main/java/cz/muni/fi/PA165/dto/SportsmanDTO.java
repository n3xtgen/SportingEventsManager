package cz.muni.fi.PA165.dto;

import java.util.Date;

public class SportsmanDTO {

	private Long id;
	private String passwordHash;
	private String name;
	private String surname;
	private String citizenIdNumber;

	public String getCitizenIdNumber() {
		return citizenIdNumber;
	}

	public void setCitizenIdNumber(String citizenIdNumber) {
		this.citizenIdNumber = citizenIdNumber;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((citizenIdNumber == null) ? 0 : citizenIdNumber.hashCode());
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
		SportsmanDTO other = (SportsmanDTO) obj;
		if (citizenIdNumber == null) {
			if (other.citizenIdNumber != null)
				return false;
		} else if (!citizenIdNumber.equals(other.citizenIdNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDTO{" +
				"id=" + id +
				", passwordHash='" + passwordHash + '\'' +
				", email='" + citizenIdNumber + '\'' +
				", givenName='" + name + '\'' +
				", surname='" + surname + '\'' +
				'}';
	}
}
