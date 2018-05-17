package beans.backing;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SessionScoped
@Named
public class LanguageController implements Serializable {

    Logger log = LogManager.getRootLogger();

    private Locale locale;

    @PostConstruct
    public void init() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();

        Cookie cookie = findLanguageCookie(request, "language");

        if (cookie == null) {
            log.info("No se encontro cookie de lenguaje, creando cookie con lenguaje local...");
            locale = ctx.getExternalContext().getRequestLocale();
            cookie = new Cookie("language", getLanguage());
            cookie.setPath(request.getContextPath());
            cookie.setMaxAge(-1);

            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            response.addCookie(cookie);
        } else {
            String language = cookie.getValue();
            log.info("Se encontro cookie de lenguaje, no es neceario crear una nueva, language: " + language);
            locale = new Locale(language);
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public void setLanguage(String language) {
        locale = new Locale(language);
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) ctx.getExternalContext().getRequest();
        Cookie cookie = findLanguageCookie(request, "language");

        if (cookie == null) {
            log.info("Cookie no encontrada en cambio de idioma, creando nueva");
            cookie = new Cookie(language, getLanguage());
        } else
            log.info("Cookie encontrada en cambio de idioma, name: " + cookie.getName() + " ,value: " + cookie.getValue());

        cookie.setValue(language);
        cookie.setPath(request.getContextPath());
        cookie.setMaxAge(-1);

        HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
        response.addCookie(cookie);

        ctx.getViewRoot().setLocale(locale);
    }

    public void changeLanguageToEnglish() {
        setLanguage("en");
    }

    public void changeLanguageToSpanish() {
        setLanguage("es");
    }

    private Cookie findLanguageCookie(HttpServletRequest request, String name) {
        Cookie[] userCookies = request.getCookies();
        if (userCookies != null && userCookies.length > 0) {
            for (Cookie userCookie : userCookies) {
                if (userCookie.getName().equals(name)) {
                    return userCookie;
                }
            }
        }
        return null;
    }
}
