package cz.muni.fi.PA165.dto;

import java.util.Date;

/**
 * 
 * @author jbouska
 */
public class SportsmanDTO {

    private Long idSportsman;
    private String name;
    private String surname;
    private String citizenIdNumber;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCitizenIdNumber() {
        return citizenIdNumber;
    }

    public void setCitizenIdNumber(String citizenIdNumber) {
        this.citizenIdNumber = citizenIdNumber;
    }

    public Long getIdSportsman() {
        return idSportsman;
    }

    public void setIdSportsman(Long idSportsman) {
        this.idSportsman = idSportsman;
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
        result = prime * result + ((getCitizenIdNumber() == null) ? 0 : getCitizenIdNumber().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SportsmanDTO other = (SportsmanDTO) obj;
        if (getCitizenIdNumber() == null) {
            if (other.getCitizenIdNumber() != null) {
                return false;
            }
        } else if (!getCitizenIdNumber().equals(other.getCitizenIdNumber())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserDTO{"
                + "id=" + idSportsman
                + ", email='" + citizenIdNumber + '\''
                + ", givenName='" + name + '\''
                + ", surname='" + surname + '\''
                + '}';
    }
}
