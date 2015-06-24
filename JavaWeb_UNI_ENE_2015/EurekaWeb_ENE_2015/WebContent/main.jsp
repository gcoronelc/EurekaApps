<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.usuario == null}">
  <jsp:forward page="index.jsp" />
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/egcc.css">
<link rel="stylesheet" type="text/css" href="menu/menu.css">
<title>EUREKA APP</title>
</head>
<body>
  <div class="_PAGE">
    <div class="_HEADER">
      <jsp:include page="header.jsp"/>
    </div>
    <div class="_BODY" id="_BODY">
    <br/><br/><br/><br/><br/><br/><br/>
    </div>
    <div class="_FOOTER">
      Derechos reservados @ EGCC-2015
    </div>
  </div>
</body>
<script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/egcc.js"></script>
</html>