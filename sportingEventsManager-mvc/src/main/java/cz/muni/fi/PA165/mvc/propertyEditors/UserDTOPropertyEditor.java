package cz.muni.fi.PA165.mvc.propertyEditors;

import cz.muni.fi.PA165.dto.UserDTO;

import java.beans.PropertyEditorSupport;

/**
 * Created by Jamik on 17.12.2015.
 */
public class UserDTOPropertyEditor extends PropertyEditorSupport {

    // indexes attributes starts inside a string
    private static final int INDEX_ID_START = 11;
    private static final int INDEX_NAME_START = 12;
    private static final int INDEX_SURNAME_START = 10;
    private static final int INDEX_EMAIL_START = 8;
    private static final int INDEX_IS_ADMIN_START = 10;

    // attributes - array mapping
    private static final int ATTR_ID = 0;
    private static final int ATTR_EMAIL = 1;
    private static final int ATTR_NAME = 2;
    private static final int ATTR_SURNAME = 3;
    private static final int ATTR_IS_ADMIN = 4;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        if(text != null && !text.isEmpty()){
            UserDTO user = new UserDTO();

            String[] attributes = text.split(";");
            // get&set id
            user.setId(Long.parseLong(attributes[ATTR_ID].substring(INDEX_ID_START)));
            // get&set email
            user.setEmail(attributes[ATTR_EMAIL].substring(INDEX_EMAIL_START, attributes[ATTR_EMAIL].length()-1));
            // get&set name
            user.setName(attributes[ATTR_NAME].substring(INDEX_NAME_START, attributes[ATTR_NAME].length()-1));
            // get&set surname
            user.setSurname(attributes[ATTR_SURNAME].substring(INDEX_SURNAME_START, attributes[ATTR_SURNAME].length()-1));
            // get&set isAdmin
            user.setAdmin(attributes[ATTR_IS_ADMIN].substring(INDEX_IS_ADMIN_START, attributes[ATTR_IS_ADMIN].length() - 2).matches("true"));

            setValue(user);
        }
        else{
            setValue(null);
        }
    }
}
