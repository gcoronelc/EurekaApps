<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="_LOGO _CAJA">
  <img alt="" src="img/logo.gif" width="80" height="40"/>
</div>
<div class="_MENU _CAJA">
  <jsp:include page="menu/menu.jsp"/>
</div>
<div class="_LOGON _CAJA">
  <br/>
  Usuario: ${sessionScope.usuario.usuario}<br/>
  <a href="LogonSalir">Salir</a>
</div>
