<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table class="form-table">
	<tr>
		<td align="right">
			<span class="badge badge-info">建檔人員</span>
		</td>
		<td>
			${entity.createdUser.userCode}&nbsp;${entity.createdUser.userName}
			<c:choose>
				<c:when test="${empty entity.id}">
					<s:hidden name="entity.createdBy" value="%{#session.login.id}" />
				</c:when>
				<c:otherwise>
					<s:hidden name="entity.createdBy" />
				</c:otherwise>
			</c:choose>
		</td>
		<td align="right">
			<span class="badge badge-info">建檔時間</span>
		</td>
		<td>
			<c:if test="${not empty entity.createdDate}">
				<s:text name="entity.createdDate" />
			</c:if>
			<s:hidden name="entity.createdDate" />
		</td>
		<td align="right">
			<span class="badge badge-info">修改人員</span>
		</td>
		<td>
			${entity.lastModifiedUser.userCode}&nbsp;${entity.lastModifiedUser.userName}
			<s:hidden name="entity.lastModifiedBy" value="%{#session.login.id}" />
		</td>
		<td align="right">
			<span class="badge badge-info">修改時間</span>
		</td>
		<td>
			<c:if test="${not empty entity.lastModifiedDate}">
				<s:text name="entity.lastModifiedDate" />
			</c:if>
			<s:hidden name="entity.lastModifiedDate" />
		</td>
	</tr>
</table>
