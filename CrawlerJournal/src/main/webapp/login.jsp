<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>
<head>
	<title>索引建立系統登入</title>
</head>

<body class="login-background">
		<s:form action="login" namespace="/authorization" method="post" cssClass="form-horizontal">
			<div align="center">
				<div align="center" class="login-location">
					<fieldset style="width:300px">
						<legend style="padding-top: 45px;color: black;">索引建立系統登入</legend>
						<label class="label label-important">帳號</label>
						<s:textfield name="user.userCode" /><br>
						<label class="label label-important">密碼</label>
						<s:password name="user.userPassword" /><br>
						<s:submit method="execute" value="登入" cssClass="btn btn-primary" />
					</fieldset>
				</div>
			</div>
		</s:form>
</body>
</html>
