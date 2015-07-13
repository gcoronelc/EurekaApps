package pe.egcc.eureka.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pe.egcc.eureka.app.db.AccesoDB;
import pe.egcc.eureka.app.domain.Cliente;

public class ClienteModel {

	public List<Cliente> buscarPorNombre(String nombre){
		List<Cliente> lista = new ArrayList<Cliente>();
		Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_cliecodigo, vch_cliepaterno, vch_cliematerno, "
              + "vch_clienombre, chr_cliedni, vch_clieciudad, "
              + "vch_cliedireccion, vch_clietelefono, vch_clieemail "
              + "from cliente where vch_cliepaterno like ? "
              + "or vch_cliematerno like ? or vch_clienombre like ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      nombre += "%";
      pstm.setString(1, nombre);
      pstm.setString(2, nombre);
      pstm.setString(3, nombre);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        Cliente bean = new Cliente();
        bean.setCodigo(rs.getString("chr_cliecodigo"));
        bean.setPaterno(rs.getString("vch_cliepaterno"));
        bean.setMaterno(rs.getString("vch_cliematerno"));
        bean.setNombre(rs.getString("vch_clienombre"));
        bean.setDni(rs.getString("chr_cliedni"));
        bean.setCiudad(rs.getString("vch_clieciudad"));
        bean.setDireccion(rs.getString("vch_cliedireccion"));
        bean.setTelefono(rs.getString("vch_clietelefono"));
        bean.setEmail(rs.getString("vch_clieemail"));
        lista.add(bean);
      }
      rs.close();
      pstm.close();
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException("ERROR: no se tiene acceso a la BD.");
    } finally {
      try {
        if (cn != null) {
          cn.close();
        }
      } catch (Exception e) {
      }
    }
		return lista;
	}
	
	public Cliente buscarPorCodigo(String codigo){
		Cliente bean = null;
		Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_cliecodigo, vch_cliepaterno, vch_cliematerno, "
              + "vch_clienombre, chr_cliedni, vch_clieciudad, "
              + "vch_cliedireccion, vch_clietelefono, vch_clieemail "
              + "from cliente where chr_cliecodigo = ? ";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, codigo);
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        bean = new Cliente();
        bean.setCodigo(rs.getString("chr_cliecodigo"));
        bean.setPaterno(rs.getString("vch_cliepaterno"));
        bean.setMaterno(rs.getString("vch_cliematerno"));
        bean.setNombre(rs.getString("vch_clienombre"));
        bean.setDni(rs.getString("chr_cliedni"));
        bean.setCiudad(rs.getString("vch_clieciudad"));
        bean.setDireccion(rs.getString("vch_cliedireccion"));
        bean.setTelefono(rs.getString("vch_clietelefono"));
        bean.setEmail(rs.getString("vch_clieemail"));
      }
      rs.close();
      pstm.close();
    } catch (SQLException e) {
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
      throw new RuntimeException("ERROR: no se tiene acceso a la BD.");
    } finally {
      try {
        if (cn != null) {
          cn.close();
        }
      } catch (Exception e) {
      }
    }
		return bean;
	}
	
	 public void modificar(Cliente bean) {
	    Connection cn = null;
	    try {
	      // Obtener el objeto connection
	      cn = AccesoDB.getConnection();
	      // Iniciar Tx
	      cn.setAutoCommit(false);
	      // Proceso
	      String sql = "update cliente "
	              + "set vch_cliepaterno = ?, vch_cliematerno = ?, "
	              + "vch_clienombre = ?, chr_cliedni = ?, "
	              + "vch_clieciudad = ?, vch_cliedireccion = ?, "
	              + "vch_clietelefono = ?, vch_clieemail = ? "
	              + "where chr_cliecodigo = ?";
	      PreparedStatement pstm = cn.prepareStatement(sql);
	      pstm.setString(1, bean.getPaterno());
	      pstm.setString(2, bean.getMaterno());
	      pstm.setString(3, bean.getNombre());
	      pstm.setString(4, bean.getDni());
	      pstm.setString(5, bean.getCiudad());
	      pstm.setString(6, bean.getDireccion());
	      pstm.setString(7, bean.getTelefono());
	      pstm.setString(8, bean.getEmail());
	      pstm.setString(9, bean.getCodigo());
	      int filas = pstm.executeUpdate();
	      if (filas != 1) {
	        throw new SQLException("Código del cliente no es correcto.");
	      }
	      pstm.close();
	      // Confirmar Tx
	      cn.commit();
	    } catch (SQLException e) {
	      // Cancelar Tx
	      try {
	        if (cn != null) {
	          cn.rollback();
	        }
	      } catch (Exception e1) {
	      }
	      // Propagar excepcion
	      throw new RuntimeException(e.getMessage());
	    } catch (Exception e) {
	      // Cancelar Tx
	      try {
	        if (cn != null) {
	          cn.rollback();
	        }
	      } catch (Exception e1) {
	      }
	      // Propagar excepcion
	      throw new RuntimeException("ERROR: no se tiene acceso a la BD.");
	    } finally {
	      try {
	        if (cn != null) {
	          cn.close();
	        }
	      } catch (Exception e) {
	      }
	    }
	  }
	
}
