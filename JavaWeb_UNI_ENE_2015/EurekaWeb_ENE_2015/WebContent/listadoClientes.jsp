<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LISTADO CLIENTES</title>
</head>
<body>
  <h1>LISTADO DE CLIENTES</h1>
  <table border="1">
    <tr>
      <th>CODIGO</th>
      <th>PATERNO</th>
      <th>MATERNO</th>
      <th>NOMBRE</th>
      <th>DNI</th>
      <th>ACCION</th>
    </tr>
    <c:forEach items="${lista}" var="r">
      <tr>
        <td>${r.codigo}</td>
        <td>${r.paterno}</td>
        <td>${r.materno}</td>
        <td>${r.nombre}</td>
        <td>${r.dni}</td>
        <td>
          <a href="javascript: fnEditarCliente('${r.codigo}');">Editar</a>&nbsp;
          <a href="javascript: fnEliminarCliente('${r.codigo}');">Eliminar</a>
        </td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>