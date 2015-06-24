package pe.egcc.eureka.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import pe.egcc.eureka.app.domain.Cliente;
import pe.egcc.eureka.app.model.ClienteModel;

@WebServlet({ "/ClienteBuscarPorNombre", "/ClienteNuevo", "/ClienteEditar",
    "/ClienteEliminar", "/ClienteGrabar" })
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/ClienteBuscarPorNombre")) {
			buscarPorNombre(request, response);
		} else if (path.equals("/ClienteNuevo")) {
			clienteNuevo(request, response);
		} else if (path.equals("/ClienteEditar")) {
			clienteEditar(request, response);
		} else if (path.equals("/ClienteEliminar")) {
			clienteEliminar(request, response);
		} else if (path.equals("/ClienteGrabar")) {
			clienteGrabar(request, response);
		}
	}

	private void clienteGrabar(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
		String accion = request.getParameter("accion");
	  switch (accion) {
		case Eureka.MANT_ACCION_NUEVO:
			creaCliente(request,response);
			break;
		case Eureka.MANT_ACCION_EDITAR:
			actualizaCliente(request,response);
			break;
		case Eureka.MANT_ACCION_ELIMINAR:
			eliminaCliente(request,response);
			break;
		}
  }

	private void creaCliente(HttpServletRequest request,
      HttpServletResponse response) {

	  
  }

	private void actualizaCliente(HttpServletRequest request,
      HttpServletResponse response) throws IOException {
	  Map<String,Object> rpta = new HashMap<String, Object>();
	  try {
	  	// Datos
	  	Cliente bean = new Cliente();
	  	bean.setCodigo(request.getParameter("codigo"));
	  	bean.setPaterno(request.getParameter("paterno"));
	  	bean.setMaterno(request.getParameter("materno"));
	  	bean.setNombre(request.getParameter("nombre"));
	  	bean.setDni(request.getParameter("dni"));
	  	bean.setCiudad(request.getParameter("ciudad"));
	  	bean.setDireccion(request.getParameter("direccion"));
	  	bean.setTelefono(request.getParameter("telefono"));
	  	bean.setEmail(request.getParameter("email"));
	  	// Proceso
	  	ClienteModel model = new ClienteModel();
	  	model.modificar(bean);
	  	// Respuest
	  	rpta.put("codigo", 1);
	  	rpta.put("mensaje", "Datos actualizados correctamente.");
    } catch (Exception e) {
    	rpta.put("codigo", -1);
	  	rpta.put("mensaje", e.getMessage());
    }
	  // Crear cadena JSON
	  Gson gson = new Gson();
	  String jsonText = gson.toJson(rpta);
	  // Envío Json
	  response.setContentType("application/json");
	  PrintWriter out = response.getWriter();
	  out.print(jsonText);
	  out.flush();
	  out.close();
  }

	private void eliminaCliente(HttpServletRequest request,
      HttpServletResponse response) {
	  
  }

	private void clienteEliminar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		// Dato
		String codigo = request.getParameter("codigo");
		// Proceso
		ClienteModel model = new ClienteModel();
		Cliente bean = model.buscarPorCodigo(codigo);
		String etiqueta = "disabled='disabled'";
		// Datos a enviar al view
		request.setAttribute("bean", bean);
		request.setAttribute("accion", Eureka.MANT_ACCION_ELIMINAR);
		request.setAttribute("etiqueta", etiqueta);
		// Forward
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("editarCliente.jsp");
		rd.forward(request, response);
	}

	private void clienteEditar(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		// Dato
		String codigo = request.getParameter("codigo");
		// Proceso
		ClienteModel model = new ClienteModel();
		Cliente bean = model.buscarPorCodigo(codigo);
		// Datos a enviar al view
		request.setAttribute("bean", bean);
		request.setAttribute("accion", Eureka.MANT_ACCION_EDITAR);
		// Forward
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("editarCliente.jsp");
		rd.forward(request, response);
	}

	private void clienteNuevo(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		// Proceso
		Cliente bean = new Cliente();
		// Datos a enviar al view
		request.setAttribute("bean", bean);
		request.setAttribute("accion", Eureka.MANT_ACCION_NUEVO);
		// Forward
		RequestDispatcher rd;
		rd = request.getRequestDispatcher("editarCliente.jsp");
		rd.forward(request, response);
	}

	private void buscarPorNombre(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {
			// Leer el paramatro
			String nombre = request.getParameter("nombre");
			// Proceso
			ClienteModel model = new ClienteModel();
			List<Cliente> lista = model.buscarPorNombre(nombre);
			if (lista.isEmpty()) {
				request.setAttribute("mensaje", "No se encontraron datos.");
				destino = "infoPage.jsp";
			} else {
				request.setAttribute("lista", lista);
				destino = "listadoClientes.jsp";
			}
		} catch (Exception e) {
			request.setAttribute("mensaje", e.getMessage());
			destino = "errorPage.jsp";
		}
		RequestDispatcher rd;
		rd = request.getRequestDispatcher(destino);
		rd.forward(request, response);
	}

}
