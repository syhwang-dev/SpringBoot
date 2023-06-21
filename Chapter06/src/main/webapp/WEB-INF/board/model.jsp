<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%-- <%
	String data = (String)request.getAttribute("data");
%> --%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Model</h1>
	<!-- EL 표기 방식 -->
	<h2>${data}</h2>
	<h2>%=data</h2>
	<%-- <c:foreach>${list} --%>
</body>
</html>