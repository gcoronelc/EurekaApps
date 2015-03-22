package pe.egcc.eureka.app.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class AccesoDB {

  private AccesoDB() {
  }

  public static Connection getConnection() throws SQLException {
    Connection cn = null;
    try {
      // --------------------------------------------------
      // Paso 1: Cargar el driver a memoria
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      // Paso 2: Obtener el objeto Connection
      String url = "jdbc:mysql://localhost:3306/eurekabank";
      cn = DriverManager.getConnection(url, "eureka", "admin");
      // --------------------------------------------------
    } catch (SQLException e) {
      throw e;
    } catch (ClassNotFoundException e) {
      throw new SQLException("No se encontró el driver de la base de datos.");
    } catch (Exception e) {
      throw new SQLException("No se puede establecer la conexión con la base de datos.");
    }
    return cn;
  }
}
