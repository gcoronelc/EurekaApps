package pe.egcc.eureka.app.controller;

import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.espec.ICuentaModel;
import pe.egcc.eureka.app.model.impl.CuentaModel;
import pe.egcc.eureka.app.util.Memoria;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public class CuentaController {

  public static void registrarDeposito(String cuenta, String clave, double importe) {
    Empleado usuario;
    usuario = (Empleado) Memoria.get("usuario");
    ICuentaModel model = new CuentaModel();
    model.registrarRetiro(cuenta, clave, importe, usuario.getCodigo());
  }
  
  public static void registrarDepositoConSP(String cuenta, String clave, double importe) {
    Empleado usuario;
    usuario = (Empleado) Memoria.get("usuario");
    ICuentaModel model = new CuentaModel();
    model.registrarRetiroConSP(cuenta, clave, importe, usuario.getCodigo());
  }
}
