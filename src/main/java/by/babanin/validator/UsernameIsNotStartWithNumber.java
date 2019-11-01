package by.babanin.validator;

import by.babanin.nls.LoginContent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.util.regex.Pattern;

@FacesValidator("by.babanin.validator.UsernameIsNotStartWithNumber")
public class UsernameIsNotStartWithNumber implements Validator {

    private static final Pattern pattern = Pattern.compile("^[\\w&&[^\\d]].*");

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (!pattern.matcher(o.toString()).find()) {
            ValidatorUtils.throwMessage(LoginContent.getInstance(), "form.login.error.username.notStartWithNumber");
        }
    }
}
