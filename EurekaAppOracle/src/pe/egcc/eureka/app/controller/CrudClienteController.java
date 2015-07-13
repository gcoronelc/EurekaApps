package pe.egcc.eureka.app.controller;

import java.util.List;
import pe.egcc.eureka.app.domain.Cliente;
import pe.egcc.eureka.app.model.impl.CrudClienteModel;
import pe.egcc.eureka.app.model.espec.ICrudCliente;

/**
 *
 * @author Gustavo Coronel
 */
public class CrudClienteController {

  public List<Cliente> traerClientes(String nombre) {
    ICrudCliente model = new CrudClienteModel();
    return model.traerPorNombre(nombre);
  }

  public Cliente traeCliente(String codigo) {
    ICrudCliente model = new CrudClienteModel();
    return model.traerPorCodigo(codigo);
  }

  public void crear(Cliente bean) {
    ICrudCliente model = new CrudClienteModel();
    model.crear(bean);
  }

  public void actualizar(Cliente bean) {
    ICrudCliente model = new CrudClienteModel();
    model.modificar(bean);
  }

  
  
  
}
