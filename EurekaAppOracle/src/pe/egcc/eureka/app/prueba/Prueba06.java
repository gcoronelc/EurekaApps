package pe.egcc.eureka.app.prueba;

import java.math.BigDecimal;

/**
 *
 * @author Gustavo Coronel
 */
public class Prueba06 {

  public static void main(String[] args) {
    BigDecimal a = new BigDecimal("0.39");
    BigDecimal b = new BigDecimal("0.19");
    BigDecimal c = a.add(b);
    BigDecimal d = a.multiply(b);
    BigDecimal e = a.add(a).add(a).add(a).add(a).add(a).add(a).add(a);
    
    System.out.println("a = " + a);
    System.out.println("b = " + b);
    System.out.println("c = " + c);
    System.out.println("d = " + d);
    System.out.println("e = " + e);
  }
  
}
