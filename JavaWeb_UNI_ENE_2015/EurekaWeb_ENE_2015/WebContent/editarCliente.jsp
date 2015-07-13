<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${accion} CLIENTE</title>
</head>
<body>
  <h1>${accion} CLIENTE</h1>
  <form id="formEditar">
    <input type="hidden" name="accion" value="${accion}" />
    <input type="hidden" name="codigo" value="${bean.codigo}" />
    <table>
      <tr>
        <td>Código:</td>
        <td>${bean.codigo}</td>
      </tr>
      <tr>
        <td>Paterno:</td>
        <td><input type="text" name="paterno" value="${bean.paterno}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>Materno:</td>
        <td><input type="text" name="materno" value="${bean.materno}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>Nombre:</td>
        <td><input type="text" name="nombre" value="${bean.nombre}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>DNI:</td>
        <td><input type="text" name="dni" value="${bean.dni}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>Ciudad:</td>
        <td><input type="text" name="ciudad" value="${bean.ciudad}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>Dirección:</td>
        <td><input type="text" name="direccion" value="${bean.direccion}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>Teléfono:</td>
        <td><input type="text" name="telefono" value="${bean.telefono}" ${etiqueta}  /></td>
      </tr>
      <tr>
        <td>Email:</td>
        <td><input type="text" name="email" value="${bean.email}" ${etiqueta}  /></td>
      </tr>
    </table>
    <input type="button" id="btnProcesar" value="Procesar"/>
  </form>
</body>
<script type="text/javascript">
   $("#btnProcesar").click(function(){
	   var data = $("#formEditar").serialize();
	   $.post("ClienteGrabar",data,function(objJson){
		   alert(objJson.mensaje);
	   });
   });
</script>
</html>