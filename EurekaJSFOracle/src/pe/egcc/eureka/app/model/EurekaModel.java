package pe.egcc.eureka.app.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.egcc.eureka.app.db.AccesoDB;
import pe.egcc.eureka.app.domain.Sucursal;

public class EurekaModel {

  public List<Sucursal> traerSucursales() {
    List<Sucursal> lista = new ArrayList<Sucursal>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_sucucodigo, vch_sucunombre, "
          + "vch_sucuciudad, vch_sucudireccion, "
          + "int_sucucontcuenta from sucursal";
      PreparedStatement pstm = cn.prepareStatement(sql);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        Sucursal bean = new Sucursal();
        bean.setCodigo(rs.getString("chr_sucucodigo"));
        bean.setNombre(rs.getString("vch_sucunombre"));
        bean.setCiudad(rs.getString("vch_sucuciudad"));
        bean.setDireccion(rs.getString("vch_sucudireccion"));
        bean.setContcuenta(rs.getInt("int_sucucontcuenta"));
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
  
  public List<String> traerSoloCuentas(String sucursal) {
    List<String> lista = new ArrayList<String>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select chr_cuencodigo from cuenta "
          + "where chr_sucucodigo = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, sucursal);
      ResultSet rs = pstm.executeQuery();
      while (rs.next()) {
        lista.add(rs.getString("chr_cuencodigo"));
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
  
  public Map<String,Object> traerDatosCuenta(String cuenta){
    Map<String,Object> datos = new HashMap<String, Object>();
    Connection cn = null;
    try {
      cn = AccesoDB.getConnection();
      String sql = "select cu.dec_cuensaldo saldo, "
          + "cu.vch_cuenestado estado, "
          + "m.vch_monedescripcion moneda, "
          + "cl.vch_cliepaterno || ' ' || vch_cliematerno "
          + "|| ', ' || vch_clienombre cliente "
          + "from cliente cl join cuenta cu "
          + "on cl.chr_cliecodigo = cu.chr_cliecodigo "
          + "join moneda m "
          + "on cu.chr_monecodigo = m.chr_monecodigo "
          + "where cu.chr_cuencodigo = ?";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        datos.put("saldo", rs.getDouble("saldo"));
        datos.put("moneda", rs.getString("moneda"));
        datos.put("estado", rs.getString("estado"));
        datos.put("cliente", rs.getString("cliente"));
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
    return datos;
  }
  
}
