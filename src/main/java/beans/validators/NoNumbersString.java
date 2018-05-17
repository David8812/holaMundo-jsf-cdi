/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author David
 */
@FacesValidator("beans.validators.NoNumbersString")
public class NoNumbersString implements Validator {

    Logger log = LogManager.getRootLogger();

    /*
    ResourceBundle.getBundle("errorMessages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
    de esta forma se pueden cargar los mensajes del fichero errorMessages
     */
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("Entrando al validador personalizado");
        String val = value.toString();
        for (int i = 0; i < val.length(); i++) {
            if (Character.isDigit(val.charAt(i))) {
                //de esta forma se cargan de los ficheros mensajes
                String msg = context.getApplication().evaluateExpressionGet(context, "#{msgs['vacanteForm.apellido.withNumber']}", String.class);
                FacesMessage message = new FacesMessage("Last name with numbers", msg);
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
