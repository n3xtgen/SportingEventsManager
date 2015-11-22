package cz.muni.fi.PA165.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author jbouska
 */
public class SportsmanAuthenticateDTO
{
     @NotNull
    private String email;
    
    @NotNull
    @Size(min = 5, max = 8)
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    
    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
