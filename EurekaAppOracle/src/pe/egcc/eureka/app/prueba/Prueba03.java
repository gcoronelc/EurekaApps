package pe.egcc.eureka.app.prueba;

import pe.egcc.eureka.app.domain.Cliente;
import pe.egcc.eureka.app.model.impl.CrudClienteModel;
import pe.egcc.eureka.app.model.espec.ICrudCliente;

/**
 *
 * @author Gustavo Coronel
 */
public class Prueba03 {

  public static void main(String[] args) {
    try {
      ICrudCliente model = new CrudClienteModel();
      Cliente bean = model.traerPorCodigo("00001");
      bean.setEmail("77777@GMAIL.COM");
      model.crear(bean);
      System.out.println("Hola " + bean.getCodigo());
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }
  
}
