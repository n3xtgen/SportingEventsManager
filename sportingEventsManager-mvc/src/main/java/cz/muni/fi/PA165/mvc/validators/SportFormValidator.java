package cz.muni.fi.PA165.mvc.validators;

import cz.muni.fi.PA165.dto.CreateSportDTO;
import cz.muni.fi.PA165.dto.SportDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;

/**
 * Created by Jamik on 5.1.2016.
 */
public class SportFormValidator implements Validator {

    private static final int HOUR_IN_MS = 3600000; // 1 hour in milliseconds
    private static final int MIN_SPORT_LENGTH = HOUR_IN_MS; // set min length to 1 hour

    @Override
    public boolean supports(Class<?> aClass) {
        return (CreateSportDTO.class.isAssignableFrom(aClass) || SportDTO.class.isAssignableFrom(aClass));
    }

    @Override
    public void validate(Object target, Errors errors) {
        Date timeNow = new Date();

        if(target instanceof CreateSportDTO){ // CreateSportDTO
            CreateSportDTO sport = (CreateSportDTO)target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.sportForm.name");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attendantsLimit", "InputEmpty.sportForm.attendantsEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.sportForm.startTimeEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.sportForm.endTimeEmpty");

            if(sport.getStartTime() != null && sport.getEndTime() != null){
                if (sport.getStartTime().getTime() >= sport.getEndTime().getTime()) {
                    errors.rejectValue("startTime", "InputEmpty.sportForm.startDateAfterEnd");
                    errors.rejectValue("endTime", "InputEmpty.sportForm.endDateBeforeStart");
                }

                if (sport.getEndTime().getTime() - sport.getStartTime().getTime() <  MIN_SPORT_LENGTH){
                    errors.rejectValue("startTime", "InputWrong.sportForm.sportTooShort");
                    errors.rejectValue("endTime", "InputWrong.sportForm.sportTooShort");
                }

                if(sport.getStartTime().compareTo(timeNow) < 0)
                    errors.rejectValue("startTime", "InputWrong.sportForm.sportCantStartInPast");

                if(sport.getEndTime().compareTo(timeNow) < 0)
                    errors.rejectValue("endTime", "InputWrong.sportForm.sportCantEndInPast");
            }
        }
        else if(target instanceof SportDTO){ // SportDTO
            SportDTO sport = (SportDTO)target;

            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "InputEmpty.sportForm.name");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "attendantsLimit", "InputEmpty.sportForm.attendantsEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime", "InputEmpty.sportForm.startTimeEmpty");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "InputEmpty.sportForm.endTimeEmpty");

            if(sport.getStartTime() != null && sport.getEndTime() != null){
                if (sport.getStartTime().getTime() >= sport.getEndTime().getTime()) {
                    errors.rejectValue("startTime", "InputEmpty.sportForm.startDateAfterEnd");
                    errors.rejectValue("endTime", "InputEmpty.sportForm.endDateBeforeStart");
                }

                if (sport.getEndTime().getTime() - sport.getStartTime().getTime() <  MIN_SPORT_LENGTH){
                    errors.rejectValue("startTime", "InputWrong.sportForm.sportTooShort");
                    errors.rejectValue("endTime", "InputWrong.sportForm.sportTooShort");
                }

                if(sport.getStartTime().compareTo(timeNow) < 0)
                    errors.rejectValue("startTime", "InputWrong.sportForm.sportCantStartInPast");

                if(sport.getEndTime().compareTo(timeNow) < 0)
                    errors.rejectValue("endTime", "InputWrong.sportForm.sportCantEndInPast");
            }
        }
    }
}
