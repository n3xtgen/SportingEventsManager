package cz.muni.fi.PA165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author jbouska
 */
public class UserDTO {

    private Long id;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    
    @NotNull
    @Size(min = 3, max = 50)
    private String surname;

    private boolean admin;
    
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean getAdmin() {
       return isAdmin();
    }

    public void setAdmin(boolean isAdmin) {
        this.admin = isAdmin;
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
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
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
        UserDTO other = (UserDTO) obj;
        if (getEmail() == null) {
            if (other.getEmail() != null) {
                return false;
            }
        } else if (!getEmail().equals(other.getEmail())) {
            return false;
        }
        return true;
    }

    /**
     * @NOTE: if you ever happen to change this method, please make sure to change dependent UserDTOPropertyEditor
     */
    @Override
    public String toString() {
        return "UserDTO{"
                + "id=" + id
                + ", email='" + getEmail() + '\''
                + ", givenName='" + name + '\''
                + ", surname='" + surname + '\''
                + ", isAdmin='" + (isAdmin() ? "true" : "false") + '\''
                + '}';
    }
}
