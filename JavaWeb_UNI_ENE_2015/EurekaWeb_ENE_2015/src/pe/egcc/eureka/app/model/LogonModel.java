package pe.egcc.eureka.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pe.egcc.eureka.app.db.AccesoDB;
import pe.egcc.eureka.app.domain.Empleado;

public class LogonModel {
	
	public Empleado validar(String usuario, String clave) {
	  Empleado bean = null;  
	  String sql = "select chr_emplcodigo, vch_emplpaterno, "
	  		+ "vch_emplmaterno, vch_emplnombre, vch_emplciudad, "
	  		+ "vch_empldireccion, vch_emplusuario "
	  		+ "from empleado where vch_emplusuario = ? "
	  		+ "and vch_emplclave = ?";
	  Connection cn = null;
	  try {
	    cn = AccesoDB.getConnection();
	    PreparedStatement pstm = cn.prepareStatement(sql);
	    pstm.setString(1, usuario);
	    pstm.setString(2, clave);
	    ResultSet rs = pstm.executeQuery();
	    if(rs.next()){
	    	bean = new Empleado();
	    	bean.setCodigo(rs.getString("chr_emplcodigo"));
	    	bean.setPaterno(rs.getString("vch_emplpaterno"));
	    	bean.setMaterno(rs.getString("vch_emplmaterno"));
	    	bean.setNombre(rs.getString("vch_emplnombre"));
	    	bean.setCiudad(rs.getString("vch_emplciudad"));
	    	bean.setDireccion(rs.getString("vch_empldireccion"));
	    	bean.setUsuario(rs.getString("vch_emplusuario"));
	    }
	    rs.close();
	    pstm.close();
	    if(bean == null){
	    	throw new SQLException("Datos incorrectos.");
	    }
    } catch (SQLException e) {
    	throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
    	throw new RuntimeException("ERROR, no se tiene acceso al servidor.");
    } finally {
    	try {
    		cn.close();
      } catch (Exception e2) {
      }
    }
	  return bean;
  }

}
