package by.babanin.validator;

import javax.faces.application.FacesMessage;
import javax.faces.validator.ValidatorException;
import java.util.ResourceBundle;

public class ValidatorUtils {
    public static FacesMessage throwMessage(ResourceBundle bundle, String errorProperty) {
        FacesMessage message = new FacesMessage(bundle.getString(errorProperty));
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        throw new ValidatorException(message);
    }
}
