package pe.egcc.eureka.app.prueba;

import pe.egcc.eureka.app.domain.Cliente;
import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.impl.CrudClienteModel;
import pe.egcc.eureka.app.model.espec.ICrudCliente;
import pe.egcc.eureka.app.model.impl.LogonModel;

/**
 *
 * @author Gustavo Coronel
 */
public class Prueba02 {

  public static void main(String[] args) {
    try {
      ICrudCliente model = new CrudClienteModel();
      Cliente bean = model.traerPorCodigo("00001");
      bean.setTelefono("996664457");
      model.modificar(bean);
      System.out.println("Hola " + bean.getNombre());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
  
}
