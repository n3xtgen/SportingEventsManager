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
        if(target instanceof CreateEntryDTO) {
            CreateEntryDTO entry = (CreateEntryDTO) target;

            // lets check empty & whitespaces
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "position", "InputEmpty.resultForm.position");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "time", "InputEmpty.resultForm.time");


            if (entry.getTime() != null){
                if (entry.getTime().getTime() == 0){
                    errors.rejectValue("Result time", "Result time has to be at least 00:00:01 hour.");
                }
            }
            else{
                errors.rejectValue("Result time", "You have to specify the result time.");
            }

        }


    }
}
