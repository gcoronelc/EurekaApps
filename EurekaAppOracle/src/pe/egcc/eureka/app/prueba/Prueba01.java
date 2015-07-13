package pe.egcc.eureka.app.prueba;

import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.impl.LogonModel;

/**
 *
 * @author Gustavo Coronel
 */
public class Prueba01 {

  public static void main(String[] args) {
    try {
      LogonModel model = new LogonModel();
      //Empleado bean = model.validar("winni", "' or '1'='1");
      Empleado bean = model.validar("lcastro", "flaca");
      System.out.println("Hola " + bean.getNombre());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
  
}
