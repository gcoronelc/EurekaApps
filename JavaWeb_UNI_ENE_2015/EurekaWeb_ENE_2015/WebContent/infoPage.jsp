<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ERROR</title>
</head>
<body>
<h1>INFO</h1>

<c:if test="${mensaje != null }">
<p class="info">${mensaje}</p>
</c:if>

<c:if test="${param.mensaje != null }">
<p class="info">${param.mensaje}</p>
</c:if>

</body>
</html>