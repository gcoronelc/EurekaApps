package pe.egcc.eureka.app.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eric Gustavo Coronel Castillo
 * @blog gcoronelc.blogspot.com
 */
public final class JdbcUtil {

  private JdbcUtil() {
  }

  public static List<Map<String, ?>> rsToList(ResultSet rs) throws SQLException {
    ResultSetMetaData md = rs.getMetaData();
    int columns = md.getColumnCount();
    List<Map<String, ?>> results = new ArrayList<Map<String, ?>>();
    while (rs.next()) {
      Map<String, Object> row = new HashMap<String, Object>();
      for (int i = 1; i <= columns; i++) {
        row.put(md.getColumnLabel(i).toLowerCase(), rs.getObject(i));
      }
      results.add(row);
    }
    return results;
  }

  public static Integer rsToInt(ResultSet rs) throws SQLException {
    Integer value = null;
    if (rs.next()) {
      value = rs.getInt(1);
    }
    return value;
  }

}
