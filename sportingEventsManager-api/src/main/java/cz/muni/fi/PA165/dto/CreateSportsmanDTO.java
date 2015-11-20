package cz.muni.fi.PA165.dto;

import java.util.Date;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author jbouska
 */
public class CreateSportsmanDTO {

    private Long id;
    
    
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String surname;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String citizenIdNumber;
    
    @NotNull
    @Size(min = 5, max = 8)
    private String password;
    
    @NotNull
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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
        CreateSportsmanDTO other = (CreateSportsmanDTO) obj;
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
                + "id=" + id
                + ", email='" + citizenIdNumber + '\''
                + ", givenName='" + name + '\''
                + ", surname='" + surname + '\''
                + '}';
    }
}
