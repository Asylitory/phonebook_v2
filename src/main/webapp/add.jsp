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
<jsp:useBean id="entryBean" class="com.lardi_trans.test.beans.EntryBean"></jsp:useBean>
<%
	entryBean.setEmptyEntry();
	request.setCharacterEncoding("UTF-8");
	if (null != request.getParameter("ADD")) {
		if (null != request.getParameter("firstname"))
			entryBean.getEntry().setFirstname(request.getParameter("firstname"));
		if (null != request.getParameter("lastname"))
			entryBean.getEntry().setLastname(request.getParameter("lastname"));
		if (null != request.getParameter("patronymic"))
			entryBean.getEntry().setPatronymic(request.getParameter("patronymic"));
		if (null != request.getParameter("phone_mobile"))
			entryBean.getEntry().setPhone_mobile(request.getParameter("phone_mobile"));
		if (null != request.getParameter("phone_home"))
			entryBean.getEntry().setPhone_home(request.getParameter("phone_home"));
		if (null != request.getParameter("address"))
			entryBean.getEntry().setAddress(request.getParameter("address"));
		if (null != request.getParameter("email"))
			entryBean.getEntry().setEmail(request.getParameter("email"));
		
		entryBean.saveEntry();
	}
%>
<title>Phonebook v2 - Добавление новой записи</title>
</head>
<body>
 <div class="header block">
  <h2 class="headerText">Phonebook v2</h2>
 </div>
 <div class="content block">
  <c:choose>
   <c:when test="${null != entryBean.errors && entryBean.errors.size() == 0}">
    <table class="information">
     <tr>
      <td>Запись была успешно сохранениа!</td>
     </tr>
     <tr>
      <td><a href="${pageContext.request.contextPath}/index.jsp" class="link">Вернуться на главную</a></td>
     </tr>
    </table>
   </c:when>
   <c:otherwise>
    <c:if test="${null != entryBean.errors}">
     <table class="information">
      <tr>
       <td>Обнаружены следующие ошибки:</td>
      </tr>
      <c:forEach var="string" items="${entryBean.errors}">
       <tr>
        <td>${string}</td>
       </tr>
      </c:forEach>
     </table>
    </c:if>
    <form action="${pageContext.request.contextPath}/add.jsp" method="POST">
    <table class="entryEdit">
     <tr>
      <td class="usualField">Имя</td>
      <td class="usualField"><input type="text" name="firstname" onkeyup="requiredFieldCheck(this, firstnameReq)" value="${entryBean.entry.firstname}"></td>
      <td class="reqField" id="firstnameReq">Обязательное поле!</td>
     </tr>
	 <tr>
      <td>Фамилия</td>
      <td><input type="text" name="lastname" onkeyup="requiredFieldCheck(this, lastnameReq)" value="${entryBean.entry.lastname}"></td>
      <td class="reqField" id="lastnameReq">Обязательное поле!</td>
     </tr>
     <tr>
      <td>Отчество</td>
      <td><input type="text" name="patronymic" onkeyup="requiredFieldCheck(this, patronymicReq)" value="${entryBean.entry.patronymic}"></td>
      <td class="reqField" id="patronymicReq">Обязательное поле!</td>
     </tr>
     <tr>
      <td>Моб.тел.</td>
      <td><input type="text" name="phone_mobile" onkeyup="requiredFieldCheck(this, phone_mobileReq)" value="${entryBean.entry.phone_mobile}"></td>
      <td class="reqField" id="phone_mobileReq">Обязательное поле!</td>
     </tr>
     <tr>
      <td>Дом.тел.</td>
      <td><input type="text" name="phone_home" value="${entryBean.entry.phone_home}"></td>
      <td></td>
     </tr>
     <tr>
      <td>Адрес</td>
      <td><input type="text" name="address" value="${entryBean.entry.address}"></td>
      <td></td>
     </tr>
     <tr>
      <td>Эл.почта</td>
      <td><input type="text" name="email" value="${entryBean.entry.email}"></td>
      <td></td>
     </tr>
     <tr class="buttons">
      <td><input type="submit" name="ADD" value="Добавить запись"></td>
      <td><input type="submit" name="CANCEL" value="Отмена" form="cancel"></td>
      <td></td>
     </tr>
    </table>
   </form>
   <form action="index.jsp" method="POST" id="cancel"></form>
   </c:otherwise>
  </c:choose>
 </div>
 <div class="footer block">
  <h3 class="footerText">Powered by Tass @ 2013</h3>
 </div>
</body>
</html>