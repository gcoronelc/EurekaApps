<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MOVIMIENTOS</title>
</head>
<body>
  <h1>MOVIMIENTOS</h1>
  <h2>CUENTA: ${cuenta}</h2>
  <table border="1">
    <tr>
      <th>NROMOV</th>
      <th>FECHA</th>
      <th>TIPO</th>
      <th>DESCRIPCION</th>
      <th>ACCION</th>
      <th>IMPORTE</th>
    </tr>
    <c:forEach items="${lista}" var="r">
      <tr>
        <td>${r.nromov}</td>
        <td>${r.fecha}</td>
        <td>${r.tipo}</td>
        <td>${r.descripcion}</td>
        <td>${r.accion}</td>
        <td>${r.importe}</td>
      </tr>
    </c:forEach>
  </table>
</body>
</html>