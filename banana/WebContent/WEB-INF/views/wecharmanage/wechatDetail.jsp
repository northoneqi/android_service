<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="<c:url value='css/style.css'/>" rel="stylesheet" type="text/css" />
<script type="text/javascript">

	function updateisshow(isshow,SKU){
		
		window.location.href="/jhyj-wechat-admin/changeIsShow.do?IsShow="+isshow+"&SKU="+SKU;
	}
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="main.do">首页</a></li>
    <li><a href="wechatgoodlist.do">商品列表</a></li>
     <li><a href="">商品明细</a></li>
    </ul>
    </div>
    <c:forEach items="${gooddetail }" var="t" varStatus="status" end="0">
    <div class="formbody">
     <ul class="toolbar1"  >
     <c:if test="${t.IsShow }">
             <li class="goodisshow" onclick="updateisshow(0,${t.SKU });"><span><img src="images/wechatmanage/t05.png" /></span>下架</li>
     
     </c:if>
     <c:if test="${!t.IsShow }">
             <li class="goodisshow" onclick="updateisshow(1,${t.SKU });"><span><img src="images/wechatmanage/t05.png" /></span>上架</li>
     
     </c:if>
        </ul>
    <div class="formtitle" style="width: 90%;"><span>商品基本信息</span></div>
    
    <div class="goodleft">
    <table >
    <tr>
     <td><b>商品编码：</b> ${t.SKU }</td><td><b>商品名称：</b>${t.ProName }</td>
    </tr>
    
    <tr>
     <td><b>商品类别：</b> ${t.ClassName }</td><td><b>售价：</b>${t.SellPrice }</td>
    </tr>
    
    <tr>
     <td><b>发布时间：</b>${t.ProAddtime }</td><td><b>上/下架：</b><c:if test="${t.IsShow }">上架</c:if> <c:if test="${!t.IsShow }">下架</c:if></td>
    </tr>
    
   
    </table>
    <div class="formtitle"><span>商品描述</span></div>
    <div class="goodright" >
	${fn:replace(t.ProDescri, "src=\"", "src=\"http://images.yijia360.com")}
    </div>
    </div>
    
     
    
   
    
    </div>
</c:forEach>
  
</body>

</html>
