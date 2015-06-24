<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.usuario == null}">
  <jsp:forward page="errorPage.jsp">
    <jsp:param value="Acceso no autorizado." name="mensaje"/>
  </jsp:forward>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CONSULTAR MOVIMIENTOS</title>
</head>
<body>
  <div>
    <form id="form1">
	    <h1>CONSULTAR MOVIMIENTOS</h1>
	    <label>Cuenta:</label><br/>
	    <input type="text" name="cuenta" />
	    <input type="button" value="Consultar" id="btnConsultar"/>
	    <input type="button" value="PDF" id="btnPdf"/>
	    <input type="button" value="Excel" id="btnExcel"/>
	    <input type="button" value="CSV" id="btnCsv"/>
    </form>
  </div>
  <div id="divMovimientos">
  </div>
</body>
<script type="text/javascript">

   $("#btnConsultar").click(function(){
	   var data = $("#form1").serialize();
	   $("#divMovimientos").load("ConsultarMovimientos",data);   
   });

   $("#btnPdf").click(function(){
	     var etiqueta = "<object data='ConsultarMovimientosPDF' " 
	     + "type='application/pdf' "
	     + "width='100%' height='500px'></object>";
	     $("#divMovimientos").html(etiqueta);   
	   });
   
</script>
</html>