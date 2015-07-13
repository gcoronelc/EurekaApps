package pe.egcc.eureka.app.util;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Coronel
 */
public final class Mensaje {

  private Mensaje() {
  }

  public static void error(Component parent, String msg) {
    JOptionPane.showMessageDialog(parent, msg,
            "EUREKA - ERROR", JOptionPane.ERROR_MESSAGE);
  }

  public static void info(Component parent, String msg) {
    JOptionPane.showMessageDialog(parent, msg,
            "EUREKA - INFO", JOptionPane.INFORMATION_MESSAGE);
  }

  public static void aviso(Component parent, String msg) {
    JOptionPane.showMessageDialog(parent, msg,
            "EUREKA - AVISO", JOptionPane.WARNING_MESSAGE);
  }

  
  
}
