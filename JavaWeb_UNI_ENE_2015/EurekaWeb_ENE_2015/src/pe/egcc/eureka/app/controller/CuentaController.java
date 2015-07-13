package pe.egcc.eureka.app.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import pe.egcc.eureka.app.domain.Empleado;
import pe.egcc.eureka.app.model.CuentaModel;

@WebServlet({ "/CuentaDeposito", "/CuentaRetiro", "/CuentaTransferencia",
    "/CuentaApertura", "/CuentaCerrar", "/ConsultarMovimientos",
    "/ConsultarMovimientosPDF", "/ConsultarMovimientosExcel" })
public class CuentaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		if (path.equals("/CuentaDeposito")) {
			cuentaDeposito(request, response);
		} else if (path.equals("/ConsultarMovimientos")) {
			consultarMovimientos(request, response);
		} else if (path.equals("/ConsultarMovimientosPDF")) {
			consultarMovimientosPDF(request, response);
		} else if (path.equals("/ConsultarMovimientosExcel")) {
			consultarMovimientosExcel(request, response);
		}
	}

	private void consultarMovimientosExcel(HttpServletRequest request,
	    HttpServletResponse response) {
		try {
			// Creación del libro
			HSSFWorkbook wb = new HSSFWorkbook();
			// Creación de lka hoja
			HSSFSheet sheet = wb.createSheet();
			// Variable para manejo de las filas
			HSSFRow row;
			// Fila 0
			row = sheet.createRow((short) 0);
			row.createCell(0).setCellValue("CUENTA");
			row.createCell(1).setCellValue("NROMOV");
			row.createCell(2).setCellValue("FECHA");
			row.createCell(3).setCellValue("TIPO");
			row.createCell(4).setCellValue("DESCRIPCION");
			row.createCell(5).setCellValue("ACCION");
			row.createCell(6).setCellValue("IMPORTE");
			// Lista de datos
			List<Map<String, ?>> lista;
			lista = (List<Map<String, ?>>) request.getSession().getAttribute(
			    "movimientos");
			// Procesar lista
			for(int n = 0; n < lista.size(); n++){
				Map<String,?> r = lista.get(n);
				row = sheet.createRow(n + 1);
				row.createCell(0).setCellValue(r.get("cuenta").toString());
				row.createCell(1).setCellValue(Integer.parseInt(r.get("nromov").toString()));
				row.createCell(2).setCellValue(r.get("fecha").toString());
				row.createCell(3).setCellValue(r.get("tipo").toString());
				row.createCell(4).setCellValue(r.get("descripcion").toString());
				row.createCell(5).setCellValue(r.get("accion").toString());
				row.createCell(6).setCellValue(Double.parseDouble(r.get("importe").toString()));
			}
			// Reporte
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
	    wb.write(outByteStream);
	    byte[] outArray = outByteStream.toByteArray();
	    response.setContentType("application/ms-excel");
	    response.setContentLength(outArray.length);
	    response.setHeader("Expires:", "0"); // eliminates browser caching
	    response.setHeader("Content-Disposition", "attachment; filename=movimientos.xls");
	    OutputStream outStream = response.getOutputStream();
	    outStream.write(outArray);
	    outStream.flush();
	    outStream.close();
		} catch (Exception e) {
		}
	}

	private void consultarMovimientosPDF(HttpServletRequest request,
	    HttpServletResponse response) {
		try {
			// Lista de datos
			List<Map<String, ?>> lista;
			lista = (List<Map<String, ?>>) request.getSession().getAttribute(
			    "movimientos");
			// Convertir lista en colección tipo Jasper
			JRMapCollectionDataSource data = new JRMapCollectionDataSource(lista);
			// Parametros
			Map<String, Object> parms = null;
			// Archivo de reporte
			String fileRepo = "/pe/egcc/eureka/app/reports/repoMovimientos.jrxml";
			InputStream ioRepo = getClass().getResourceAsStream(fileRepo);
			// Crear el reporte
			JasperReport jsRepo = JasperCompileManager.compileReport(ioRepo);
			byte[] bytes = JasperRunManager.runReportToPdf(jsRepo, parms, data);
			// Enviando el reporte
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream out = response.getOutputStream();
			out.write(bytes, 0, bytes.length);
			out.flush();
			out.close();
		} catch (Exception e) {
		}
	}

	private void consultarMovimientos(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {
			// Datos
			String cuenta = request.getParameter("cuenta");
			// Proceso
			CuentaModel model = new CuentaModel();
			List<Map<String, ?>> lista = model.conMovimientos(cuenta);
			request.getSession().setAttribute("movimientos", lista);
			request.setAttribute("cuenta", cuenta);
			request.setAttribute("lista", lista);
			destino = "listaMovimientos.jsp";
		} catch (Exception e) {
			request.setAttribute("mensaje", e.getMessage());
			destino = "errorPage.jsp";
		}
		request.getRequestDispatcher(destino).forward(request, response);
	}

	private void cuentaDeposito(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
		String destino;
		try {
			// Datos
			String cuenta = request.getParameter("cuenta");
			double importe = Double.parseDouble(request.getParameter("importe"));
			// Usuario
			Empleado bean;
			bean = (Empleado) request.getSession().getAttribute("usuario");
			// Proceso
			CuentaModel model = new CuentaModel();
			model.ejecutarDeposito(cuenta, importe, bean.getCodigo());
			request.setAttribute("mensaje", "Proceso ejecutado correctamente.");
			destino = "infoPage.jsp";
		} catch (Exception e) {
			request.setAttribute("mensaje", e.getMessage());
			destino = "errorPage.jsp";
		}
		request.getRequestDispatcher(destino).forward(request, response);
	}

}
