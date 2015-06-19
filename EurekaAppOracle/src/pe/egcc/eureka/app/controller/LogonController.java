package pe.egcc.eureka.app.controller;

import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.espec.ILogonModel;
import pe.egcc.eureka.app.model.impl.LogonModel;
import pe.egcc.eureka.app.util.Memoria;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public class LogonController {

  public void validar(String usuario, String clave) {
    ILogonModel model = new LogonModel();
    Empleado bean = model.validar(usuario, clave);
    Memoria.put("usuario", bean);
  }

  
}
