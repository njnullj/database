<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="referrer" content="no-referrer" />
  <title>MFS</title>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/bootstrap2.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/navbar.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/semantic.css">
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css">

  <script src="<%=request.getContextPath() %>/js/jquery.js"></script>
  <script src="<%=request.getContextPath() %>/js/semantic.js"></script>
  <script src="<%=request.getContextPath() %>/js/basic.js"></script>
  <script src="<%=request.getContextPath() %>/js/code.js"></script>
</head>
<body>
  <%@ include file="topbar.jsp" %>
	<div class="container">
		<div class="row">
			<div class="span4">   
				<div class="ui vertical menu">
				  <a class="item" href='<c:url value="/followings"/>' >
				    关注
				  </a>
				  <a class="active teal item" href='<c:url value="/followers"/>'>
				    发现
				  </a>
				</div>
			
			</div>
			<div class="span8">
				<div class="ui header">
					好友的好友
				</div>
				<div class="ui divider">
				</div>

				<div class="ui cards" id='ui_cards'>				
				
				</div>
				<!-- end cards -->

			</div>
		</div>
	
	</div>
	<script src="<%=request.getContextPath() %>/js/followers.js"></script>
</body>

</html>