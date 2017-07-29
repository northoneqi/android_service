<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="${basePath}/">
     <style type="text/css">
		body {
				TEXT-ALIGN: center;
				background-image: url("${basePath}/ext/resources/images/default/layout/gradient-bg.gif");
			 }
		a { color: white; }
	 </style>
    <title>首页</title>
     <script type="text/javascript" src="${basePath}/js/jquery.js"></script>
	 <script type="text/javascript" src="${basePath}/js/interface.js"></script>
	 <link type="text/css" href="${basePath}/css/interface-fisheye.css" rel="stylesheet">
  </head>
  <body>
  	<div id="fisheye" class="fisheye">
		<div class="fisheyeContainter">
		<a href="${basePath}/main.do" class="fisheyeItem"><img src="${basePath}/images/accordion/accod_8.png" width="30" /><span>微信商城后台</span></a> 
			<c:forEach items="${resourceList}" var="resource">
				<a href="${basePath}/admin/index.do?fcode=${resource.code}" class="fisheyeItem"><img src="${basePath}/images/accordion/${resource.icon}" width="30" /><span>${resource.name}</span></a>
			</c:forEach>
		</div>
	</div>
<script type="text/javascript">

$(document).ready(
	$('#fisheye').Fisheye(
		{
			maxWidth: 160,
			items: 'a',
			itemsText: 'span',
			container: '.fisheyeContainter',
			itemWidth: 100,
			proximity: 120,
			halign : 'center'
		}
	)
);

</script>
  <body>
</html>
