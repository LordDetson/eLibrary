package by.babanin.validator;

import by.babanin.dao.UserRepository;
import by.babanin.nls.LoginContent;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("by.babanin.validator.UsernameIsExist")
public class UsernameIsExist implements Validator {
    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        if (UserRepository.getInstance().isExistUsername(o.toString())){
            ValidatorUtils.throwMessage(LoginContent.getInstance(), "form.login.error.username.isExist");
        }
    }
}
