package by.babanin.model.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;

@ManagedBean
@SessionScoped
public class LocaleChanger implements Serializable {
    private static final String LANG = "lang.";
    private Locale currentLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    public LocaleChanger() {
    }

    public void changeLocale(String language, String country) {
        setCurrentLocale(new Locale(language, country));
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public String getKeyLangContent() {
        return LANG + currentLocale.getLanguage();
    }
}
