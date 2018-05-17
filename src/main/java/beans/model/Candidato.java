package beans.model;

import java.util.Date;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@RequestScoped
@Named
public class Candidato {

    Logger log = LogManager.getRootLogger();

    private String nombre;
    private String apellido;
    private int sueldoDeseado;
    private Date fechaNacimiento;
    private String[] array;

    public Candidato() {
        log.info("Creando el objeto Candidato");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
        log.info("Modificando la propiedad nombre:" + this.nombre);
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
        log.info("Modificando la propiedad apellido:" + this.apellido);
    }

    public int getSueldoDeseado() {
        return sueldoDeseado;
    }

    public void setSueldoDeseado(int sueldoDeseado) {
        this.sueldoDeseado = sueldoDeseado;
        log.info("Modificando la propiedad sueldoDeseado:" + this.sueldoDeseado);
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        log.info("Modificando la propiedad fechaNacimiento:" + this.fechaNacimiento);
    }

    public void setArray(String[] array) {
        this.array = array;
        log.info("Modificando la propiedad array: ");
        for (int i = 0; i < array.length; i++) {
            String string = array[i];
            log.info("valor " + i + " :" + string);
        }
    }

    public String[] getArray() {
        return array;
    }
}
