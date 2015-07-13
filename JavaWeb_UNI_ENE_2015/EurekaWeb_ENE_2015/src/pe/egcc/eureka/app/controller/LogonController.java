package pe.egcc.eureka.app.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.LogonModel;

@WebServlet({ "/LogonIngresar", "/LogonSalir" })
public class LogonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if(path.equals("/LogonIngresar")){
			logonIngresar(request,response);
		} else if(path.equals("/LogonSalir")){
			logonSalir(request,response);
		}
	}

	private void logonSalir(HttpServletRequest request,
      HttpServletResponse response) 
      throws ServletException, IOException {
	  request.getSession().invalidate();
	  RequestDispatcher rd;
	  rd = request.getRequestDispatcher("index.jsp");
	  rd.forward(request, response);
  }

	private void logonIngresar(HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
		String destino;
	  try {
	    // Datos
	  	String usuario = request.getParameter("usuario");
	  	String clave = request.getParameter("clave");
	  	// Proceso
	  	LogonModel model = new LogonModel();
	  	Empleado bean = model.validar(usuario, clave);
	  	request.getSession().setAttribute("usuario", bean);
	  	destino = "main.jsp";
    } catch (Exception e) {
	    request.setAttribute("mensaje", e.getMessage());
	    destino = "index.jsp";
    }
	  RequestDispatcher rd;
	  rd = request.getRequestDispatcher(destino);
	  rd.forward(request, response);
  }

}
