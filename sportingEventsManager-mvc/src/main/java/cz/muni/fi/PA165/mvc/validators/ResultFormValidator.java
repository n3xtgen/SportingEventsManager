package cz.muni.fi.PA165.mvc.validators;


import cz.muni.fi.PA165.dto.CreateEntryDTO;
import cz.muni.fi.PA165.dto.EntryDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import java.util.Date;
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
            
            for (int i = 0; i < entries.size(); i++) {
                EntryDTO entry = entries.get(i);
                
                if (entry.getEntryState() == null) {
                    errors.rejectValue("entries[" + i + "].entryState", "InputEmpty.resultForm.entryState");
                }
                if (entry.getEntryState().equals(EntryDTO.EntryState.FINISHED)) {
                    Date time = entry.getTime();
                    if(time == null) {
                        errors.rejectValue("entries[" + i + "].time", "InputWrong.resultForm.time");
                    }
                } else {
                    Date time = entry.getTime();
                    if(time != null) {
                        errors.rejectValue("entries[" + i + "].time", "InputWrong.resultForm.timeSet");
                    }
                }
            }
            
            return;
        }
    }
}
