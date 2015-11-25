package cz.muni.fi.PA165.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Vladimir on 24.11.2015.
 */
public class CreateSportDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
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
        if (!getName().equals(((CreateSportDTO)obj).getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CreateSportDTO{"
                + "name=" + name
                + '}';
    }

}
