package pe.egcc.eureka.app.model.espec;

import pe.egcc.eureka.app.domain.Empleado;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public interface ILogonModel {

  Empleado validar(String usuario, String clave);

}
