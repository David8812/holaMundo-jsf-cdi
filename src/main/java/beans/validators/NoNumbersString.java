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

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        log.info("Entrando al validador personalizado");
        String val = value.toString();
        for (int i = 0; i < val.length(); i++) {
            if (Character.isDigit(val.charAt(i))) {
                FacesMessage message = new FacesMessage("Fallo la validacion del apellido.", "El apellido no puede contener numeros");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
