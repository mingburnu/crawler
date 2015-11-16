<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>

<script type="text/javascript">
	$(function() {
		$("#accordion").accordion({
			heightStyle : "fill"
		});
	});
</script>

<head>
<title>索引系統</title>
</head>

<body>
<jsp:include page="/WEB-INF/jsp/layout/menu.jsp" />
<p>等待建立....</p>
</body>
</html>