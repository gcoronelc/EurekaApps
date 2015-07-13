package pe.egcc.eureka.app.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import pe.egcc.eureka.app.domain.Cliente;
import pe.egcc.eureka.app.model.ClienteModel;

@ManagedBean
@SessionScoped
public class MantClientesControl {

  private String nombre;
  private List<Cliente> lista;
  private Cliente bean;
  private String accion = null;
  private boolean eliminar;
  
  @PostConstruct
  public void initBean(){
    nombre = "%";
    doConsultar();
  }
  
  public boolean isEliminar() {
    return eliminar;
  }
  
  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public List<Cliente> getLista() {
    return lista;
  }
  public void setLista(List<Cliente> lista) {
    this.lista = lista;
  }
  
  public Cliente getBean() {
    if(bean == null){
      bean = new Cliente();
    }
    return bean;
  }
  
  public void setBean(Cliente bean) {
    this.bean = bean;
  }
  
  public String getAccion() {
    return accion;
  }
  
  public void setAccion(String accion) {
    this.accion = accion;
  }
  
  public void doConsultar() {
    try {
      ClienteModel model = new ClienteModel();
      lista = model.buscarPorNombre(nombre);
    } catch (Exception e) {
    }
  }
  
  public void doNuevo(){
    accion = EurekaUtil.MANT_NUEVO;
    bean = new Cliente();
    bean.setCodigo("NONE");
    eliminar = false;
  }
  
  public void setClienteEditar(Cliente r){
    accion = EurekaUtil.MANT_EDITAR;
    bean = r.clone();
    eliminar = false;
  }
  
  public void setClienteEliminar(Cliente r){
    accion = EurekaUtil.MANT_ELIMINAR;
    bean = r;
    eliminar = true;
  }
  
  public void doGrabar() {
    FacesMessage msg = null;
    ClienteModel model = new ClienteModel();
    try {
      switch(accion){
      case EurekaUtil.MANT_EDITAR:
        // Actualiza la base de datos
        model.modificar(bean);
        // Actualizar la lista
        for(int i = 0; i < lista.size(); i++){
          Cliente obj = lista.get(i);
          if(obj.getCodigo().equals(bean.getCodigo())){
            lista.set(i, bean);
            break; // salir del for
          }
        }
        break; // salir del switch
      case EurekaUtil.MANT_NUEVO:
        model.insertar(bean);
        lista.add(0, bean);
        break;
      case EurekaUtil.MANT_ELIMINAR:
        model.eliminar(bean.getCodigo());
        // Actualizar la lista
        for(int i = 0; i < lista.size(); i++){
          Cliente obj = lista.get(i);
          if(obj.getCodigo().equals(bean.getCodigo())){
            lista.remove(i);
            break; // salir del for
          }
        }
        break;
      }
      msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
          "INFO", "Proceso " + accion + " ejecutado correctamente.");
    } catch (Exception e) {
      msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
          "ERROR", e.getMessage());
    }
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }
  
}
