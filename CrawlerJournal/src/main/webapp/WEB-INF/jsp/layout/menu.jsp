<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<jsp:include page="/WEB-INF/jsp/layout/javascript.jsp" />
<jsp:include page="/WEB-INF/jsp/layout/css.jsp" />

<style>
.ui-menu {
	overflow: hidden;
}

.ui-menu .ui-menu {
	overflow: visible !important;
}

.ui-menu>li {
	float: left;
	display: block;
	width: auto !important;
}

.ui-menu ul li {
	display: block;
	float: none;
}

.ui-menu ul li ul {
	left: 120px !important;
	width: 100%;
}

.ui-menu ul li ul li {
	width: auto;
}

.ui-menu ul li ul li a {
	float: left;
}

.ui-menu>li {
	margin: 5px 5px !important;
	padding: 0 0 !important;
}

.ui-menu>li>a {
	float: left;
	display: block;
	clear: both;
	overflow: hidden;
}

.ui-menu .ui-menu-icon {
	margin-top: 0.3em !important;
}

.ui-menu .ui-menu .ui-menu li {
	float: left;
	display: block;
}

.disabled>a {
	color: #aaa !important;
	cursor: default;
}

.disabled>a:hover,.disabled>a:focus,.disabled>a:active
.disabled>a.active,.disabled>a.disabled,.disabled>a[disabled],.disabled>a:after
	{
	border-color: #cccccc #cccccc #cccccc;
	background-color: #f7f7f9;
	background-image: -moz-linear-gradient(top, #f7f7f9, #f7f7f9);
	background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#f7f7f9),
		to(#f7f7f9));
	background-image: -webkit-linear-gradient(top, #f7f7f9, #f7f7f9);
	background-image: -o-linear-gradient(top, #f7f7f9, #f7f7f9);
	background-image: linear-gradient(to bottom, #f7f7f9, #f7f7f9);
	background-repeat: repeat-x;
}
</style>

<script>
	$(function() {
		var $menu = $("#menu");
		$menu.menu();
		$menu.find("ul").each(function() {
			$(this).css("z-index", "1");
		});

	});
</script>
<ul id="menu">
	<li><a href='<c:url value="/page/section.action" />'>首頁</a></li>
	<li><a
		href='<s:url namespace="/crud" action="security.secUser.list" />'>帳號管理</a></li>
	<li><a
		href='<s:url namespace="/crud" action="setting.journalData.list" />'>索引</a></li>
	<li><a id="logout"
		href='<s:url namespace="/authorization" action="logout" />'>登出</a></li>
</ul>
