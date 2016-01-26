package cz.muni.fi.PA165.mvc.propertyEditors;

import cz.muni.fi.PA165.dto.EventDTO;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Created by Jamik on 23.1.2016.
 */
public class EventDTOPropertyEditor extends PropertyEditorSupport {

    // attributes order inside of the string
    private static final int ATTR_ID = 0;
    private static final int ATTR_NAME = 1;
    private static final int ATTR_DESC = 2;
    private static final int ATTR_START_TIME = 3;
    private static final int ATTR_END_TIME = 4;

    // positions of attribute start in the string
    private static final int INDEX_ID_START = 13;
    private static final int INDEX_NAME_START = 7;
    private static final int INDEX_DESC_START = 14;
    private static final int INDEX_START_TIME_START = 8;
    private static final int INDEX_END_TIME_START = 6;

    /**
     * setAsText
     *
     * converts EventDTO from string to object
     * @param text
     * @throws IllegalArgumentException
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if(text != null && !text.isEmpty()){
            EventDTO event = new EventDTO();
            String[] attributes = text.split(";");

            // set idEvent
            event.setIdEvent(Long.parseLong(attributes[ATTR_ID].substring(INDEX_ID_START)));
            // set name
            event.setName(attributes[ATTR_NAME].substring(INDEX_NAME_START));
            // set description
            event.setDescription(attributes[ATTR_DESC].substring(INDEX_DESC_START));
            // set start time & end time
            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy", Locale.ENGLISH);
            try {
                event.setStartTime(dateFormat.parse(attributes[ATTR_START_TIME].substring(INDEX_START_TIME_START)));
                event.setEndTime(dateFormat.parse(attributes[ATTR_END_TIME].substring(INDEX_END_TIME_START, attributes[ATTR_END_TIME].length()-1)));
            }catch (ParseException ex){}

            setValue(event);
        }
        else
            setValue(null);
    }
}
