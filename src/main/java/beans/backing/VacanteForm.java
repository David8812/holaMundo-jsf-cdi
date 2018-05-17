package beans.backing;

import beans.helper.FacesContextHelper;
import beans.model.Candidato;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SessionScoped
@Named
public class VacanteForm implements Serializable {

    Logger log = LogManager.getRootLogger();

    @Inject
    private Candidato candidato;

    private boolean comentarioEnviado = false;

    public VacanteForm() {
        log.info("Creando objeto VacanteForm");
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    //Metodo de flujo de control
    public String enviar() {

        System.out.println("enviar() Nombre=" + this.candidato.getNombre());
        System.out.println("enviar() Apellido=" + this.candidato.getApellido());
        System.out.println("enviar() Sueldo deseado=" + this.candidato.getSueldoDeseado());

        if (this.candidato.getNombre().equals("Juan")) {

            if (this.candidato.getApellido().equals("Perez")) {
                String msg = "Gracias, pero Juan Perez ya trabaja con nosotros.";
                FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                String clientId = null; //Este es un mensaje global
                facesContext.addMessage(clientId, facesMessage);
                return "index";
            }
            return "exito";//exito.xhtml
        } else {
            return "fallo"; //fallo.xhtml
        }
    }

    //Metodo de tipo Value Change Listener
    public void codigoPostalListener(ValueChangeEvent valueChangeEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIViewRoot uiViewRoot = facesContext.getViewRoot();
        String newCodigoPostal = (String) valueChangeEvent.getNewValue();
        String colonia, ciudad;

        switch (newCodigoPostal) {
            case "03810":
                colonia = "Napoles";
                ciudad = "Ciudad de Mexico";
                break;
            case "11450":
                colonia = "Ahuehuetes Anahuac";
                ciudad = "Ciudad de Mexico";
                break;
            default:
                colonia = "";
                ciudad = "";
                log.info("Asignando codigo postal a candidato: " + newCodigoPostal);
              //  candidato.setCodigoPostal(newCodigoPostal);
                break;
        }
        //Utilizamos el nombre del form de index.xhtml para encontrar el componente
        UIInput coloniaInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:colonia");
        coloniaInputText.setValue(colonia);
        coloniaInputText.setSubmittedValue(colonia);

        UIInput ciudadInputText = (UIInput) uiViewRoot.findComponent("vacanteForm:ciudad");
        ciudadInputText.setValue(ciudad);
        ciudadInputText.setSubmittedValue(ciudad);

        facesContext.renderResponse();
    }

    public void ocultarComentario(ActionEvent actionEvent) {
        this.comentarioEnviado = !this.comentarioEnviado;
        log.info("Mostrando/ocultando el comentario");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesContextHelper.limpiarImmediateFacesMessages(facesContext);
    }

    public boolean isComentarioEnviado() {
        return comentarioEnviado;
    }

    public void setComentarioEnviado(boolean comentarioEnviado) {
        this.comentarioEnviado = comentarioEnviado;
    }

}
