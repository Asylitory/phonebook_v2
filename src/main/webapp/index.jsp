<%@page import="org.apache.taglibs.standard.tlv.JstlBaseTLV"%>
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
%>
<title>Phonebook v2</title>
</head>
<body> 
 <div class="header block">
  <h2 class="headerText">Phonebook v2</h2>
 </div>
 <div class="content block">
  <table class="information">
   <tr>
    <td>Info string #1</td>
   </tr>
   <tr>
    <td>Info string #2</td>
   </tr>
   <tr>
    <td>Info string #3</td>
   </tr>
  </table>
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
    <c:forEach var="entry" items="${entriesListBean}">
     <tr>
      <td><input type="checkbox" onclick="uncheckSelectAll(this)" class="onDelete" name="id=${entry.id}"></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp">${entry.lastname} ${entry.firstname} ${entry.patronymic}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp">${entry.phone_mobile}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp">${entry.phone_home}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp">${entry.address}</a></td>
      <td><a href="${pageContext.request.contextPath}/edit.jsp">${entry.email}</a></td>
     </tr>
    </c:forEach>
   </table>
  </form>
  <form method="POST" action="${pageContext.request.contextPath}/add.jsp" id="newEntry"></form>
  <table class="controls">
   <tr>
    <td><input type="submit" form="deleteSelected" value="Удалить выделенные" name="deleteAll"></td>
    <td><input type="submit" form="newEntry" value="Создать новую запись" name="createNew"></td>
   </tr>
  </table>
 </div>
 <div class="footer block">
  <h3 class="footerText">Powered by Tass @ 2013</h3>
 </div>
</body>
</html>