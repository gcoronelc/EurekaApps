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
<title>DEPOSITO</title>
</head>
<body>
<h1>DEPOSITO</h1>
<form id="form1">
  
  <label>Cuenta:</label>
  <input type="text" name="cuenta" /><br/>
  
  <label>Importe:</label>
  <input type="text" name="importe" /><br/>
  
  <input type="button" value="Procesar" id="btnProcesar" />
</form>
</body>
<script type="text/javascript">

  $("#btnProcesar").click(function(){
	  var data = $("#form1").serialize();
	  $("#_BODY").load("CuentaDeposito",data);
  });

</script>
</html>