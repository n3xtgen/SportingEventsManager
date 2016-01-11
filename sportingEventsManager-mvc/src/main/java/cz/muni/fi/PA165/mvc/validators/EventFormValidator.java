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
        Date timeNow = new Date();
        if(target instanceof CreateEventDTO) {
            CreateEventDTO evt = (CreateEventDTO) target;

            // lets check empty & whitespaces
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.eventForm.name");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "InputEmpty.eventForm.description");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.eventForm.startDate");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.eventForm.endDate");

            if (evt.getStartTime() != null && evt.getEndTime() != null) {
                // check if the start date is before end date
                if (evt.getStartTime().getTime() >= evt.getEndTime().getTime()) {
                    errors.rejectValue("startTime", "InputEmpty.eventForm.startDateAfterEnd");
                    errors.rejectValue("endTime", "InputEmpty.eventForm.endDateBeforeStart");
                }

                // TODO: use MIN_EVENT_LENGTH in error message
                // lets say we want only events that last at least MIN_EVENT_LENGTH
                else if (evt.getEndTime().getTime() - evt.getStartTime().getTime() < MIN_EVENT_LENGTH) {
                    errors.rejectValue("startTime", "InputWrong.eventForm.eventTooShort");
                    errors.rejectValue("endTime", "InputWrong.eventForm.eventTooShort");
                }

                // start in the past?
                if(evt.getStartTime().compareTo(timeNow) > 0)
                    errors.rejectValue("startTime", "InputWrong.eventForm.eventCantStartInPast");

                // end in the past?
                if(evt.getEndTime().compareTo(timeNow) > 0)
                    errors.rejectValue("endTime", "InputWrong.eventForm.eventCantEndInPast");
            }
        }
        else{
            EventDTO evt = (EventDTO) target;

            // lets check empty & whitespaces
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.eventForm.name");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "InputEmpty.eventForm.description");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.eventForm.startDate");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.eventForm.endDate");

            if (evt.getStartTime() != null && evt.getEndTime() != null) {
                // check if the start date is before end date
                if (evt.getStartTime().getTime() >= evt.getEndTime().getTime()) {
                    errors.rejectValue("startTime", "InputEmpty.eventForm.startDateAfterEnd");
                    errors.rejectValue("endTime", "InputEmpty.eventForm.endDateBeforeStart");
                }

                // TODO: use MIN_EVENT_LENGTH in error message
                // lets say we want only events that last at least MIN_EVENT_LENGTH
                if (evt.getEndTime().getTime() - evt.getStartTime().getTime() < MIN_EVENT_LENGTH) {
                    errors.rejectValue("startTime", "InputWrong.eventForm.eventTooShort");
                    errors.rejectValue("endTime", "InputWrong.eventForm.eventTooShort");
                }

                if(evt.getStartTime().compareTo(timeNow) > 0)
                    errors.rejectValue("startTime", "InputWrong.eventForm.eventCantStartInPast");

                if(evt.getEndTime().compareTo(timeNow) > 0)
                    errors.rejectValue("endTime", "InputWrong.eventForm.eventCantEndInPast");
            }
        }
    }
}
