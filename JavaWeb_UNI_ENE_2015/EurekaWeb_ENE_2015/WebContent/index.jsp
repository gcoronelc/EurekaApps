<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/egcc.css">
<title>INGRESO AL SISTEMA</title>
<style type="text/css">

  #divLogon{
    width: 200px;
    margin: 10px auto;
    padding: 5px;
    background-color: white;
  }

</style>
</head>
<body>
  <div id="divLogon">
    <img alt="" src="img/logo.gif" height="30" width="100">
    <h1>INGRESO AL SISTEMA</h1>

    <c:if test="${mensaje != null}">    
    <p class="error">${mensaje}</p>
    </c:if>
    
    <form method="post" action="LogonIngresar">
    
      <label>Usuario:</label><br/>
      <input type="text" name="usuario"/><br/>
    
      <label>Clave:</label><br/>
      <input type="password" name="clave"/><br/><br/>
    
      <div style="text-align: center; ">
        <input type="submit" value="Ingresar" />
      </div>
      
    </form>
  </div>
</body>
</html>