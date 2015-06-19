package pe.egcc.eureka.app.model.espec;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public interface ICuentaModel {

  /**
   * Proceso ejecutado solo con JDBC.
   *
   * @param cuenta
   * @param clave
   * @param importe
   * @param empleado
   */
  void registrarRetiro(String cuenta, String clave, double importe, String empleado);

  /**
   * Proceso ejecutado con SP.
   * 
   * @param cuenta
   * @param clave
   * @param importe
   * @param empleado 
   */
  void registrarRetiroConSP(String cuenta, String clave, double importe, String empleado);

}
