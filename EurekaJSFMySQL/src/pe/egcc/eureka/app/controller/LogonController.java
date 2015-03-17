package pe.egcc.eureka.app.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.LogonModel;

@ManagedBean
@SessionScoped
public class LogonController {

  private String usuario;
  private String clave;
  private Empleado empleado;

  
  public String getUsuario() {
    return usuario;
  }
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
  public String getClave() {
    return clave;
  }
  public void setClave(String clave) {
    this.clave = clave;
  }
  
  public Empleado getEmpleado() {
    return empleado;
  }
  
  public String doProcesar(){
    String destino;
    FacesMessage msg;
    try {
      LogonModel model = new LogonModel();
      empleado = model.validar(usuario, clave);
      destino = "main";
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
          "Bienvenido.", "Bienvenido " + empleado.getNombre());
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
          "Error en el proceso.", e.getMessage());
      destino = "index";
    }
    FacesContext.getCurrentInstance().addMessage(null, msg);
    return destino;
  }
  
}
