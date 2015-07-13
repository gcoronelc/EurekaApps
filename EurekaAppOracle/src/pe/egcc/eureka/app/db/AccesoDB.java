package pe.egcc.eureka.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public final class AccesoDB {

  private AccesoDB() {
  }
  
  public static Connection getConnection() throws SQLException{
    Connection cn = null;
    try{
      // Datos
      String driver = "oracle.jdbc.OracleDriver";
      String url = "jdbc:oracle:thin:@localhost:1521:XE";
      String user = "eureka";
      String pass = "admin";
      // Cargar el driver
      Class.forName(driver).newInstance();
      // Establecer la conexión
      cn = DriverManager.getConnection(url, user, pass);
    }catch(SQLException e){
      throw e;
    }catch(ClassNotFoundException e){
      throw new SQLException("ERROR, no existe el driver.");
    } catch(Exception e){
      throw new SQLException("ERROR, no se tiene acceso al servidor.");
    } 
    return cn;
  }
}
