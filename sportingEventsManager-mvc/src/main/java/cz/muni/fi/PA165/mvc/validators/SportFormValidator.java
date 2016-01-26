package cz.muni.fi.PA165.mvc.validators;

import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.EventDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Created by Jamik on 5.1.2016.
 */
public class SportFormValidator implements Validator {

    private static final int MIN_SPORT_LENGTH = 600000; // set minimal length of a sport to 10 minutes

    @Override
    public boolean supports(Class<?> aClass) {
        return (CreateSportDTO.class.isAssignableFrom(aClass) || SportDTO.class.isAssignableFrom(aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {

        if(target instanceof CreateSportDTO){ // CreateSportDTO
            CreateSportDTO sport = (CreateSportDTO)target;

            validateForm(sport.getStartTime(), sport.getEndTime(), sport.getEvent(),errors);
        }
        else if(target instanceof SportDTO) { // SportDTO
            SportDTO sport = (SportDTO) target;

            validateForm(sport.getStartTime(), sport.getEndTime(),sport.getEvent(),errors);
        }
    }

    private void validateForm(Date sTime, Date eTime, EventDTO event, Errors errors){
        Date timeNow = new Date();

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.sportForm.name");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.sportForm.startTimeEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.sportForm.endTimeEmpty");

        if(sTime != null && eTime != null){
            if (sTime.getTime() >= eTime.getTime()) {
                errors.rejectValue("startTime", "InputEmpty.sportForm.startDateAfterEnd");
                errors.rejectValue("endTime", "InputEmpty.sportForm.endDateBeforeStart");
            }

            if (eTime.getTime() - sTime.getTime() <  MIN_SPORT_LENGTH){
                errors.rejectValue("startTime", "InputWrong.sportForm.sportTooShort");
                errors.rejectValue("endTime", "InputWrong.sportForm.sportTooShort");
            }

            if(sTime.compareTo(timeNow) < 0)
                errors.rejectValue("startTime", "InputWrong.sportForm.sportCantStartInPast");

            if(eTime.compareTo(timeNow) < 0)
                errors.rejectValue("endTime", "InputWrong.sportForm.sportCantEndInPast");

            if(!isWithinEventBounds(sTime, event.getStartTime(), event.getEndTime()))
                errors.rejectValue("startTime", "InputWrong.sportForm.sportStartOutsideOfEventBounds");

            if(!isWithinEventBounds(eTime, event.getStartTime(), event.getEndTime()))
                errors.rejectValue("endTime", "InputWrong.sportForm.sportEndOutsideOfEventBounds");
        }
        // else - shit is about to get real
    }

    /**
     * check if a date is inside of an time interval
     * @param dateToCheck
     * @param lowerBound
     * @param upperBound
     * @return
     */
    private boolean isWithinEventBounds(Date dateToCheck, Date lowerBound, Date upperBound){

        if(dateToCheck.compareTo(lowerBound) < 0 || dateToCheck.compareTo(upperBound) > 0)
            return false;

        return true;
    }
}
