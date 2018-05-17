/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.converters;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author David
 */
@FacesConverter("beans.converters.ToArrayString")
public class ToArrayString implements Converter {

    Logger log = LogManager.getRootLogger();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        log.info("Entrando al convertidor personalizado getAsObject()");
        if (value != null && !value.trim().equals("")) {
            log.info("Value no es null, es: " + value);
            String[] ret = value.split("[,]");
            return ret;
        } else {
            String msg = context.getApplication().evaluateExpressionGet(context, "#{msgs['vacanteForm.array.empty']}", String.class);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", msg);
            throw new ConverterException(message);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        log.info("Entrando al convertidor personalizado getAsString()");
        String[] array = (String[]) value;
        if (value != null && array.length > 0) {
            String ret = "";
            for (int i = 0; i < array.length; i++) {
                if (i > 0) {
                    ret += ",";
                }
                ret += array[i];
            }
            log.info("Array string: " + ret);
            return ret;
        } else {
            String msg = context.getApplication().evaluateExpressionGet(context, "#{msgs['vacanteForm.array.empty']}", String.class);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de conversión", msg);
            throw new ConverterException(message);
        }
    }
}
