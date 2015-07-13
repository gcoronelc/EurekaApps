package pe.egcc.eureka.app.model.espec;

import java.util.List;

/**
 *
 * @author Gustavo Coronel
 * @param <T>
 */
public interface ICrudGeneral<T> {
  
  T traerPorCodigo(String codigo);
  
  List<T> traerPorNombre(String nombre);
  
  void crear(T bean);
  
  void modificar(T bean);
  
  void eliminar(String codigo);
  
}
