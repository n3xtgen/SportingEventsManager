package cz.muni.fi.PA165.mvc.propertyEditors;

import cz.muni.fi.PA165.dto.SportDTO;

import java.beans.PropertyEditorSupport;

/**
 * Created by Jamik on 17.12.2015.
 */
public class SportDTOPropertyEditor extends PropertyEditorSupport {

    // positions of attribute starts in the string
    private static final int INDEX_ID_START = 12;
    private static final int INDEX_NAME_START = 6;

    // attributes order inside of the string
    private static final int ATTR_ID = 0;
    private static final int ATTR_NAME = 1;

    /**
     * setAsText(String text)
     *
     * converts String from .jsp from to a SportDTO object
     * @param text
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        System.out.println("SportDTOPropertyEditor: "+ text );

        if(text != null && !text.isEmpty()) {

            SportDTO sport = new SportDTO();

            String[] attributes = text.split(",");
            // get&set sportId from the string
            long id = Long.parseLong(attributes[ATTR_ID].substring(INDEX_ID_START));
            sport.setIdSport(id);
            // set name
            sport.setName(attributes[ATTR_NAME].substring(INDEX_NAME_START, attributes[ATTR_NAME].length()-1));
            System.out.println("SportDTOPropertyEditor after processing: " + sport.getIdSport() + " , name: " + sport.getName());
            setValue(sport);
        }
        else // may not happen
            setValue(null);
    }
}
