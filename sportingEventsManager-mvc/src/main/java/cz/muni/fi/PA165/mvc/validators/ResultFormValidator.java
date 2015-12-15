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


//            if (entry.getStartTime() != null && entry.getEndTime() != null) {
//                // check if the start date is before end date
//                if (entry.getStartTime().getTime() >= entry.getEndTime().getTime()) {
//                    errors.rejectValue("startTime", "InputEmpty.eventForm.startDateAfterEnd");
//                    errors.rejectValue("endTime", "InputEmpty.eventForm.endDateBeforeStart");
//                }
//
//                // TODO: cant use reject value like this
//                // lets say we want only events that last at least MIN_EVENT_LENGTH
//                if (entry.getEndTime().getTime() - entry.getStartTime().getTime() < MIN_EVENT_LENGTH) {
//                    errors.rejectValue("startTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
//                    errors.rejectValue("endTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
//                }
//            }
        }
//        else{
//            EntryDTO evt = (EntryDTO) target;
//
//            // lets check empty & whitespaces
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.eventForm.name");
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "InputEmpty.eventForm.description");
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.eventForm.startDate");
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.eventForm.endDate");
//
//            if (evt.getStartTime() != null && evt.getEndTime() != null) {
//                // check if the start date is before end date
//                if (evt.getStartTime().getTime() >= evt.getEndTime().getTime()) {
//                    errors.rejectValue("startTime", "InputEmpty.eventForm.startDateAfterEnd");
//                    errors.rejectValue("endTime", "InputEmpty.eventForm.endDateBeforeStart");
//                }
//
//                // TODO: cant use reject value like this
//                // lets say we want only events that last at least MIN_EVENT_LENGTH
//                if (evt.getEndTime().getTime() - evt.getStartTime().getTime() < MIN_EVENT_LENGTH) {
//                    errors.rejectValue("startTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
//                    errors.rejectValue("endTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
//                }
//            }
//        }
    }
}
