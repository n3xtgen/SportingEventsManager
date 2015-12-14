package cz.muni.fi.PA165.mvc.validators;

import cz.muni.fi.PA165.dto.CreateEventDTO;;
import cz.muni.fi.PA165.dto.EventDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

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

                // TODO: cant use reject value like this
                // lets say we want only events that last at least MIN_EVENT_LENGTH
                if (evt.getEndTime().getTime() - evt.getStartTime().getTime() < MIN_EVENT_LENGTH) {
                    errors.rejectValue("startTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
                    errors.rejectValue("endTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
                }
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

                // TODO: cant use reject value like this
                // lets say we want only events that last at least MIN_EVENT_LENGTH
                if (evt.getEndTime().getTime() - evt.getStartTime().getTime() < MIN_EVENT_LENGTH) {
                    errors.rejectValue("startTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
                    errors.rejectValue("endTime", "Event has to last at least " + (MIN_EVENT_LENGTH / HOUR_IN_MS) + " hour.");
                }
            }
        }
    }
}
