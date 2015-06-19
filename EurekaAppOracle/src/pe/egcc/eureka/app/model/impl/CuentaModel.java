package pe.egcc.eureka.app.model.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import pe.egcc.eureka.app.db.AccesoDB;
import pe.egcc.eureka.app.model.espec.ICuentaModel;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public class CuentaModel implements ICuentaModel {

  @Override
  public void registrarRetiro(String cuenta, String clave, double importe, String empleado) {
    Connection cn = null;
    try {
      // Obtener el objeto connection
      cn = AccesoDB.getConnection();
      // Iniciar Tx
      cn.setAutoCommit(false);
      // Leer datos de la cuenta
      String sql = "select dec_cuensaldo, int_cuencontmov "
              + "from cuenta "
              + "where chr_cuencodigo = ? and chr_cuenclave = ? "
              + "for update";
      PreparedStatement pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      pstm.setString(2, clave);
      ResultSet rs = pstm.executeQuery();
      if (!rs.next()) {
        rs.close();
        pstm.close();
        throw new SQLException("ERROR, datos incorrectos.");
      }
      double saldo = rs.getDouble("dec_cuensaldo") - importe;
      int cont = rs.getInt("int_cuencontmov") + 1;
      rs.close();
      pstm.close();
      // Verificar saldo
      if (saldo < 0.0) {
        throw new SQLException("ERROR, no tiene saldo suficiente.");
      }
      // Verificar limite
      sql = "select sum(dec_moviimporte) total "
              + "from movimiento "
              + "where chr_cuencodigo = ? "
              + "and chr_tipocodigo = '004' "
              + "and extract(month from dtt_movifecha) = extract(month from sysdate)";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      rs = pstm.executeQuery();
      rs.next();
      double total = rs.getDouble("total");
      rs.close();
      pstm.close();
      if (total >= 2000.0) {
        throw new SQLException("ERROR, ya retiro el limite del mes.");
      }
      // Actualizar cuenta
      sql = "update cuenta "
              + "set dec_cuensaldo = ?, int_cuencontmov = ? "
              + "where chr_cuencodigo = ? ";
      pstm = cn.prepareStatement(sql);
      pstm.setDouble(1, saldo);
      pstm.setInt(2, cont);
      pstm.setString(3, cuenta);
      pstm.executeUpdate();
      pstm.close();
      // Registrar el movimiento
      sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
              + "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,dec_moviimporte) "
              + "values(?,?,sysdate,?,'004',?)";
      pstm = cn.prepareStatement(sql);
      pstm.setString(1, cuenta);
      pstm.setInt(2, cont);
      pstm.setString(3, empleado);
      pstm.setDouble(4, importe);
      pstm.executeUpdate();
      pstm.close();
      // Confirmar Tx
      cn.commit();
      System.err.println("PROCESO: Sin procedimiento.");
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

  @Override
  public void registrarRetiroConSP(String cuenta, String clave, double importe, String empleado) {
    Connection cn = null;
    try {
      // Obtener el objeto connection
      cn = AccesoDB.getConnection();
      // Iniciar Tx
      cn.setAutoCommit(true);
      // Proceso
      String sql = "{call egcc_retiro(?,?,?,?)}";
      CallableStatement cstm = cn.prepareCall(sql);
      cstm.setString(1, cuenta);
      cstm.setDouble(2, importe);
      cstm.setString(3, empleado);
      cstm.setString(4, clave);
      cstm.executeUpdate();
      cstm.close();
      System.err.println("PROCESO: Con procedimiento.");
    } catch (SQLException e) {
      // Propagar excepcion      
      throw new RuntimeException(e.getMessage());
    } catch (Exception e) {
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
