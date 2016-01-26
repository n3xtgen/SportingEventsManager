package cz.muni.fi.PA165.mvc.validators;

import cz.muni.fi.PA165.dto.CreateEventDTO;;
import cz.muni.fi.PA165.dto.EventDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import java.util.Date;

/**
 * Created by Jamik on 13.12.2015.
 */

public class EventFormValidator implements Validator {

    private static final int HOUR_IN_MS = 3600000; // 1 hour in milliseconds
    private static final int MIN_EVENT_LENGTH = HOUR_IN_MS; // set min length to 1 hour

    @Override
    public boolean supports(Class<?> aClass) {
        return (CreateEventDTO.class.isAssignableFrom(aClass) || EventDTO.class.isAssignableFrom(aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(target instanceof CreateEventDTO) {
            CreateEventDTO evt = (CreateEventDTO) target;

            validateForm(evt.getStartTime(), evt.getEndTime(), errors, false);
        }
        else{
            EventDTO evt = (EventDTO) target;

            validateForm(evt.getStartTime(), evt.getEndTime(), errors, true);
        }
    }

    private void validateForm(Date sTime, Date eTime, Errors errors, boolean isUpdate){
        Date timeNow = new Date();
        // lets check empty & whitespaces
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.eventForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "InputEmpty.eventForm.description");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.eventForm.startDate");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.eventForm.endDate");

        if (sTime != null && eTime != null) {
            // check if the start date is before end date
            if (sTime.getTime() >= eTime.getTime()) {
                errors.rejectValue("startTime", "InputEmpty.eventForm.startDateAfterEnd");
                errors.rejectValue("endTime", "InputEmpty.eventForm.endDateBeforeStart");
            }

            // TODO: use MIN_EVENT_LENGTH in error message
            // lets say we want only events that last at least MIN_EVENT_LENGTH
            if (eTime.getTime() - sTime.getTime() < MIN_EVENT_LENGTH) {
                errors.rejectValue("startTime", "InputWrong.eventForm.eventTooShort");
                errors.rejectValue("endTime", "InputWrong.eventForm.eventTooShort");
            }

            if(!isUpdate) {
                if (sTime.compareTo(timeNow) < 0)
                    errors.rejectValue("startTime", "InputWrong.eventForm.eventCantStartInPast");

                if (eTime.compareTo(timeNow) < 0)
                    errors.rejectValue("endTime", "InputWrong.eventForm.eventCantEndInPast");
            }
        }
    }
}
