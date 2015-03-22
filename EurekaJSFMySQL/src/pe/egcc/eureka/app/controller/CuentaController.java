package pe.egcc.eureka.app.controller;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import pe.egcc.eureka.app.model.CuentaModel;

@ManagedBean
@RequestScoped
public class CuentaController {

  private String cuenta;
  private double importe;
  private List<Map<String, ?>> movimientos;
  
  @ManagedProperty("#{logonController}")
  private LogonController logonController;
  
  public void setLogonController(LogonController logonController) {
    this.logonController = logonController;
  }
  
  public void setCuenta(String cuenta) {
    this.cuenta = cuenta;
  }
  
  public String getCuenta() {
    return cuenta;
  }
  
  public void setImporte(double importe) {
    this.importe = importe;
  }
  
  public double getImporte() {
    return importe;
  }
  
  public List<Map<String, ?>> getMovimientos() {
    return movimientos;
  }
  
  public void doDeposito(){
	FacesMessage msg;
	try {
		// Proceso
		String codEmpl = logonController.getEmpleado().getCodigo();
		CuentaModel model = new CuentaModel();
		model.ejecutarDeposito(cuenta, importe, codEmpl);
		// Limpiar variables
		cuenta = "";
		importe = 0;
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"INFO", "El depósito se registro correctamente.");
	} catch (Exception e) {
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
				"ERROR", e.getMessage());
	}
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
  
  public void doRetiro(){
    
  }
  
  public void doTraerListado(){
    CuentaModel model = new CuentaModel();
    movimientos = model.conMovimientos(cuenta);
  }
}
