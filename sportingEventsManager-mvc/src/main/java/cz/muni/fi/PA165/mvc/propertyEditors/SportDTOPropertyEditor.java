package cz.muni.fi.PA165.mvc.propertyEditors;

import cz.muni.fi.PA165.dto.SportDTO;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Jamik on 17.12.2015.
 */
public class SportDTOPropertyEditor extends PropertyEditorSupport {

    // positions of attribute starts in the string
    private static final int INDEX_ID_START = 12;
    private static final int INDEX_NAME_START = 6;
    private static final int INDEX_START_TIME_START = 11;
    private static final int INDEX_END_TIME_START = 9;

    // attributes order inside of the string
    private static final int ATTR_ID = 0;
    private static final int ATTR_NAME = 1;
    private static final int ATTR_START_TIME = 2;
    private static final int ATTR_END_TIME = 3;

    /**
     * setAsText(String text)
     *
     * converts String from .jsp form to a SportDTO object
     * @param text
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if(text != null && !text.isEmpty()) {

            SportDTO sport = new SportDTO();

            String[] attributes = text.split(",");
            // get&set sportId from the string
            long id = Long.parseLong(attributes[ATTR_ID].substring(INDEX_ID_START));
            sport.setIdSport(id);
            // set name
            sport.setName(attributes[ATTR_NAME].substring(INDEX_NAME_START, attributes[ATTR_NAME].length()-1));

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);

            try {
                sport.setStartTime(dateFormat.parse(attributes[ATTR_START_TIME].substring(INDEX_START_TIME_START)));
                sport.setEndTime(dateFormat.parse(attributes[ATTR_END_TIME].substring(INDEX_END_TIME_START, attributes[ATTR_END_TIME].length()-1)));
            }catch (ParseException ex){}

            setValue(sport);
        }
        else // may not happen
            setValue(null);
    }
}
