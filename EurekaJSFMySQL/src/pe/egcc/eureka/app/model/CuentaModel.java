package pe.egcc.eureka.app.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import oracle.jdbc.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import pe.egcc.eureka.app.db.AccesoDB;
import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.util.JdbcUtil;

public class CuentaModel {

	public void ejecutarDeposito(String cuenta, double importe, String empleado) {
		Connection cn = null;
		try {
			// Obteniendo el objeto Connection
			cn = AccesoDB.getConnection();
			// Inicio de Tx
			cn.setAutoCommit(false);
			// Paso 1: Leer datos de cuneta
			String sql = "select dec_cuensaldo, int_cuencontmov " + "from cuenta "
			    + "where chr_cuencodigo = ? " + "for update ";
			PreparedStatement pstm = cn.prepareStatement(sql);
			pstm.setString(1, cuenta);
			ResultSet rs = pstm.executeQuery();
			if (!rs.next()) {
				throw new SQLException("Cuenta no existe.");
			}
			double saldo = rs.getDouble("dec_cuensaldo");
			int cont = rs.getInt("int_cuencontmov");
			rs.close();
			pstm.close();
			// Paso 2: Actualizar cuenta
			saldo += importe;
			cont++;
			sql = "update cuenta " + "set dec_cuensaldo = ?, "
			    + "int_cuencontmov = ? " + "where chr_cuencodigo = ?";
			pstm = cn.prepareStatement(sql);
			pstm.setDouble(1, saldo);
			pstm.setInt(2, cont);
			pstm.setString(3, cuenta);
			pstm.executeUpdate();
			pstm.close();
			// Paso 3: Registrar movimiento
			sql = "insert into movimiento(chr_cuencodigo,int_movinumero,"
			    + "dtt_movifecha,chr_emplcodigo,chr_tipocodigo,"
			    + "dec_moviimporte, chr_cuenreferencia) "
			    + "values(?,?,sysdate,?,'003',?,null)";
			pstm = cn.prepareStatement(sql);
			pstm.setString(1, cuenta);
			pstm.setInt(2, cont);
			pstm.setString(3, empleado);
			pstm.setDouble(4, importe);
			pstm.executeUpdate();
			pstm.close();
			// Confirmar
			cn.commit();
		} catch (SQLException e) {
			try {
				if (cn != null)
					cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException(e.getMessage());
		} catch (Exception e) {
			try {
				if (cn != null)
					cn.rollback();
			} catch (Exception e1) {
			}
			throw new RuntimeException("ERROR, no se puede ejecutar el proceso.");
		} finally {
			try {
				if (cn != null)
					cn.close();
			} catch (Exception e) {
			}
		}
	}
	
	public List<Map<String, ?>> conMovimientos(String cuenta){
		List<Map<String, ?>> lista = null;
		Connection cn = null;
	  try {
	    cn = AccesoDB.getConnection();
	    String sql = "{call usp_egcc_movimientos(?)}";
	    CallableStatement cstm = cn.prepareCall(sql);
	    cstm.setString(1, cuenta);
	    ResultSet rs = cstm.executeQuery();
	    lista = JdbcUtil.rsToList(rs);
	    rs.close();
	    cstm.close();
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
		return lista;
	}
}
