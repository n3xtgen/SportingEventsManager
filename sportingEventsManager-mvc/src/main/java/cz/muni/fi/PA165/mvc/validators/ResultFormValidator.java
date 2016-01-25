package cz.muni.fi.PA165.mvc.validators;


import cz.muni.fi.PA165.dto.CreateEntryDTO;
import cz.muni.fi.PA165.dto.EntryDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by Eli on 15.12.2015.
 */
public class ResultFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return (CreateEntryDTO.class.isAssignableFrom(aClass) || EntryDTO.class.isAssignableFrom(aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target instanceof CreateEntryDTO) {
            CreateEntryDTO entry = (CreateEntryDTO) target;
        } else if (target instanceof EntryDTO) {
            EntryDTO entry = (EntryDTO) target;
            
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
        }
    }
}
