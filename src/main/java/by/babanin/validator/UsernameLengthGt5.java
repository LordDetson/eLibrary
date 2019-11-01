package by.babanin.validator;

import by.babanin.nls.LoginContent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("by.babanin.validator.UsernameLengthGt5")
public class UsernameLengthGt5 implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (o.toString().length() < 5) {
            ValidatorUtils.throwMessage(LoginContent.getInstance(), "form.login.error.username.lengthGt5");
        }
    }
}
