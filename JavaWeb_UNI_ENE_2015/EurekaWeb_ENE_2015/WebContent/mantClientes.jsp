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
<title>Insert title here</title>
</head>
<body>
  <div>
	  <form id="form1">
	    <h1>MANTENIMIENTO DE CLIENTES</h1>
	    <label>Nombre</label><br/>
	    <input type="text" name="nombre"/>
	    <input type="button" value="Buscar" id="btnBuscar" />
	    <input type="button" value="Nuevo" id="btnNuevo" />
	  </form>
  </div>
  <div id="divResultado">
  </div>
</body>
<script type="text/javascript">

  $("#btnBuscar").click(function(){
	  var data = $("#form1").serialize();
	  var loadImg = "<img alt='' src='img/ajax-loader.gif'/>";	  
	  $("#divResultado").html(loadImg);
	  $.post("ClienteBuscarPorNombre",data,function(html){
		  $("#divResultado").html(html);
	  });
  });
  
  $("#btnNuevo").click(function(){
	    $("#divResultado").load("ClienteNuevo");   
	});
  
  function fnEditarCliente( codigo ){ 
	  var data = "codigo=" + codigo;
	  $("#divResultado").load("ClienteEditar", data);
  } 
  
  function fnEliminarCliente( codigo ){ 
	    var data = "codigo=" + codigo;
	    $("#divResultado").load("ClienteEliminar", data);
	} 
</script>
</html>