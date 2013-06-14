<%@page import="com.lardi_trans.test.classes.Entry"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-latest.js"></script>
<jsp:useBean id="entriesListBean" class="com.lardi_trans.test.beans.EntriesListBean"></jsp:useBean>
<%
	entriesListBean.getEntries();

	if (null != request.getParameter("DELETE")) {
		for (Entry entry : entriesListBean.getEntriesList()) {
			if (null != request.getParameter("id" + entry.getId())) {
				entriesListBean.setToDelete(entry);
			}
		}
		if (entriesListBean.getDeletedEntries().size() > 0) {
			entriesListBean.deleteEntries();
			entriesListBean.getEntries();
		}
	}
%>
<title>Phonebook v2</title>
</head>
<body> 
 <div class="header block">
  <h2 class="headerText">Phonebook v2</h2>
 </div>
 <div class="content block">
  <c:if test="${entriesListBean.deletedEntries.size() > 0}">
   <table class="information">
    <tr>
     <td>Были удалены следующие записи:</td>
    </tr>
    <c:forEach var="entry" items="${entriesListBean.deletedEntries}">
     <tr>
      <td>${entry.lastname} ${entry.firstname} ${entry.patronymic}</td>
     </tr>
    </c:forEach>
   </table>
  </c:if>
  <form method="POST" action="${pageContext.request.contextPath}/index.jsp" id="massDelete">
   <table class="data">
    <tr>
     <th><input type="checkbox" onclick="switchAll(this)" id="selectAll"></th>
     <th>Ф.И.О.</th>
     <th>Моб. тел.</th>
     <th>Дом. тел.</th>
     <th>Адрес</th>
     <th>Эл.почта</th>
    </tr>
    <c:forEach var="entry" items="${entriesListBean.entriesList}">
     <tr>
      <td><input type="checkbox" onclick="uncheckSelectAll(this)" class="onDelete" name="id${entry.id}"></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp?id=${entry.id}" class="link">${entry.lastname} ${entry.firstname} ${entry.patronymic}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp?id=${entry.id}" class="link">${entry.phone_mobile}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp?id=${entry.id}" class="link">${entry.phone_home}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp?id=${entry.id}" class="link">${entry.address}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp?id=${entry.id}" class="link">${entry.email}</a></td>
     </tr>
    </c:forEach>
   </table>
  </form>
  <form method="POST" action="${pageContext.request.contextPath}/add.jsp" id="newEntry"></form>
  <table class="controls">
   <tr>
    <td><input type="submit" form="massDelete" value="Удалить выделенные" name="DELETE"></td>
    <td><input type="submit" form="newEntry" value="Создать новую запись" name="createNew"></td>
   </tr>
  </table>
 </div>
 <div class="footer block">
  <h3 class="footerText">Powered by Tass @ 2013</h3>
 </div>
</body>
</html>