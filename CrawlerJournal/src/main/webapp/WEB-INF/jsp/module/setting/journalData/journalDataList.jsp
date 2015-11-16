<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <s:set var="theme" value="'simple'" scope="page" /> --%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<!DOCTYPE HTML>
<html>
<head>
<title>摘要讀取</title>
</head>
<script type="text/javascript"
	src='<c:url value="/resources/js/jquery-1.10.2.js" />'></script>
<script type="text/javascript">
	$(document).ready(function() {
		var i = $("div.alert.alert-error").length;
		if (i == 2) {
			$("div.alert.alert-error:eq(0)").remove();
		}

		var j = $("div.alert.alert-success").length;
		if (j == 2) {
			$("div.alert.alert-success:eq(0)").remove();
		}
	});
</script>
<body class="page-padding">
	<jsp:include page="/WEB-INF/jsp/layout/menu.jsp" />
	<fieldset>
		<legend>期刊資料管理</legend>
		<%-- 搜尋區塊 --%>
		<s:form action="setting.journalData.list" namespace="/crud"
			method="post" cssClass="form-search">
			<table class="form-table">
				<!--<tr>
					<td class="form-label"><label class="label">使用者代號</label></td>
					<td><s:textfield name="entity.authorChName"
							cssClass="input-medium search-query" /></td>
					<td class="form-label"><label class="label">使用者名稱</label></td>
					<td><s:textfield name="entity.authorEnName"
							cssClass="input-medium search-query" /></td>
				</tr>-->
				<tr>
					<td colspan="4" align="right">
						<div class="btn-group">
							<s:submit cssClass="btn btn-info" value="論文列表" />
							<input type="button" class="btn btn-success" value="新增資料"
								onclick="document.location='<s:url namespace="/crud" action="setting.journalData.save" />'" />
							<input type="button" class="btn btn-warning" value="全部匯出"
								onclick="document.location='<s:url namespace="/crud" action="setting.journalData.writeAll" />'" />
						</div>
					</td>
				</tr>
			</table>
		</s:form>

		<div>
			<%--<s:form action="setting.journalData.writeDate" namespace="/crud">
				<input type="text" name="start" />
				<input type="text" name="end"  />
				<s:submit cssClass="btn btn-info" value="部分匯出" />
			</s:form> --%>
			<form id="setting_journalData_writeDate"
				name="setting_journalData_writeDate"
				action="<c:url value="/crud/setting.journalData.writeDate.action"/>"
				method="post">
				開始日期<input type="date" name="start" value="2014-08-01">結束日期<input
					type="date" name="end" value=""><input type="submit"
					id="setting_journalData_writeDate_0" value="部分匯出"
					class="btn btn-danger">
			</form>
		</div>

		<div>
			<a class="btn btn-warning"
				href="<c:url value="/resources/media/files/excel_all.xlsx"/>">excel_all檔</a>
			<a class="btn btn-danger"
				href="<c:url value="/resources/media/files/excel_date.xlsx"/>">excel_date檔</a>
		</div>
	</fieldset>

	<%-- 分頁 --%>
	<table style="width: 100%">
		<tr>
			<td><jsp:include page="/WEB-INF/jsp/layout/pagination.jsp">
					<jsp:param name="namespace" value="/crud" />
					<jsp:param name="action" value="setting.journalData.list" />
					<jsp:param name="pager" value="${ds.pager}" />
				</jsp:include></td>
		</tr>
	</table>


	<%-- List區塊 --%>
	<table class="table table-hover" style="width: 100%">
		<thead>
			<tr class="list-head">
				<th>研究生</th>
				<th>研究生(外文)</th>
				<th>論文名稱</th>
				<th>論文名稱(外文)</th>
				<th>指導教授</th>
				<th>指導教授(外文)</th>
				<th>學位類別</th>
				<th>校院名稱</th>
				<th>系所名稱</th>
				<th>論文出版年</th>
				<th>畢業學年度</th>
				<th>中文關鍵字</th>
				<th>英文關鍵字</th>
				<th>中文摘要</th>
				<th>英文摘要</th>
				<th>本論文永久網址</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${ds.results}" varStatus="status">
				<c:set var="editPage">
					<s:url namespace="/crud" action="setting.journalData.query">
						<s:param name="entity.id">${item.id}</s:param>
					</s:url>
				</c:set>
				<tr class="list-item${status.index mod 2}">
					<td>${item.authorChName}</td>
					<td>${item.authorEnName}</td>
					<td>${item.paperChTitle}</td>
					<td>${item.paperEnTitle}</td>
					<td>${item.professorChName}</td>
					<td>${item.professorEnName}</td>
					<td>${item.graduateLevel}</td>
					<td>${item.college}</td>
					<td>${item.department}</td>
					<td>${item.publishYr}</td>
					<td>${item.graduatedYr}</td>
					<td>${item.chKeyWords}</td>
					<td>${item.enKeyWords}</td>
					<td>${item.chAbstract}</td>
					<td>${item.enAbstract}</td>
					<td>${item.url}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>