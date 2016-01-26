package cz.muni.fi.PA165.mvc.validators;


import cz.muni.fi.PA165.dto.CreateEntryDTO;
import cz.muni.fi.PA165.dto.EntryDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import java.util.List;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Eli on 15.12.2015.
 */
public class ResultFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return (SportDTO.class.isAssignableFrom(aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof SportDTO) {
            SportDTO sportDTO = (SportDTO) target;
            
            List<EntryDTO> entries = sportDTO.getEntries();
            /*
            for (EntryDTO entry : entries) {
                ValidationUtils.rejectIfEmptyOrWhitespace(errors, "idEntry", "Time cannot be empty when finished.");
                
                EntryDTO.EntryState entryState = entry.getEntryState();
                switch (entryState) {
                    case REGISTERED:
                        break;
                    case FINISHED:
                        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "Time cannot be empty when finished.");
                        break;
                    case DISQUALIFIED:
                        break;
                }
            }*/
            
            return;
        }
    }
}
