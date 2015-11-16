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
		// 		$("#contentTr").css("height", screen.height - 270);
		// 		$("#contentTd").css("width", screen.width - 180);
		$("#contentTd").mouseover(function() {
			$("#menu").menu("collapseAll", null, true);
		});
	});
</script>

<head>
<title>索引系統</title>
<meta http-equiv="refresh" content="1; url=<c:url value="/page/section.action" />" />
</head>
<body class="index-background">
<jsp:include page="/WEB-INF/jsp/layout/menu.jsp" />
</body>
</html>
