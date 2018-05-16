<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="service.ForwardService" %>
<%
	if (session.getAttribute("name") != null) {
		new ForwardService(request,response,"/view/login.jsp","セッションタイムアウト");
	}
%>