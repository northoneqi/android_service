<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" uri="http://javass.cn/common/" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>商品</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>

<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});
  
  
  $(".searchclick").click(function(){
	  
	 $("#formsearch").submit();
	
  });
  
  $(".resetclick").click(function(){
		 $("#goodname").val("");
		 $("#goodsku").val("");
		 $("#goodclass").val("");
		 $("select").val("r");
	  });

});


function checkall(){
	
		if($("#allcheck").attr("checked")){
			 $(".checktr :checkbox").attr("checked", true);   
		}else {
			 $(".checktr :checkbox").attr("checked", false);   
		}
	
       
    
};  
</script>


</head>


<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="main.do">首页</a></li>
     <li><a href="wechatgoodlist.do">商品列表</a></li>
  
    </ul>
    </div>
    
    <div class="rightinfo">
    <form action="<c:url value='wechatgoodlist.do'/>"  id="formsearch" name="findForm">
     <div class="searchgood">
    	<table >
    	<tr>
    	<td>商品名称:<input type="text" value="${filter.goodname}" id="goodname"  name="goodname"/> </td>
    	<td>商品SKU:<input type="text" value="${filter.goodsku}" id="goodsku"  name="goodsku"/></td>
    	<td>商品类别:<input type="text" value="${filter.goodclass}"  id="goodclass" name="goodclass"/> </td>
    	<td>商品上/下架:
    	<select name="goodisshow">
    	<option value="r" disabled selected="selected">请选择</option>
    	<option value="0" <c:if test="${filter.goodisshow==0}">selected</c:if>>下架</option>
    	<option value="1" <c:if test="${filter.goodisshow==1}">selected</c:if>>上架</option>
    	</select> </td>
    	</tr>
    	</table>
    
    </div>
    
    
    <div class="tools">
    
    	<!-- <ul class="toolbar">
        <li class="click"><span><img src="images/wechatmanage/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/wechatmanage/t02.png" /></span>修改</li>
        <li><span><img src="images/wechatmanage/t03.png" /></span>删除</li>
        <li><span><img src="images/wechatmanage/t04.png" /></span>统计</li>
        
        </ul> -->
        
        <ul class="toolbar0">
        <li class="resetclick" ><span><img src="images/wechatmanage/t02.png"  onclick="findForm.reset()"/></span>重置</li>
        </ul>
        <ul class="toolbar1">
        <li class="searchclick" ><span><img src="images/wechatmanage/t05.png" /></span>查询</li>
        </ul>
    
    </div>
    </form>
    
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" id="allcheck" onclick="checkall();"/></th>
        <th>SKU<i class="sort"><img src="images/wechatmanage/px.gif" /></i></th>
        <th>商品名称</th>
        <th>商品类别</th>
        <th>售价</th>
        <th>发布时间</th>
        <th>上/下架</th>
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
       <c:forEach items="${page.items}" var="t" varStatus="status">
        <tr class="checktr">
        <td><input name="" type="checkbox" value="" /></td>
        <td>${t.SKU }</td>
        <td>${t.ProName }</td>
        <td>${t.ClassName }</td>
        <td>${t.SellPrice }</td>
        <td>${t.ProAddtime }</td>
        <td><c:if test="${t.IsShow }">上架</c:if> <c:if test="${!t.IsShow }">下架</c:if></td>
        <td><a href="<c:url value='wechatgooddetail.do?SKU=${t.SKU }'/> " class="tablelink">查看</a>    <!--  <a href="#" class="tablelink"> 删除</a> --></td>
        </tr> 
       </c:forEach>
        
      
        </tbody>
    </table>
    
   
    <div class="pagin">
    	<!-- <div class="message">共<i class="blue">1256</i>条记录，当前显示第&nbsp;<i class="blue">2&nbsp;</i>页</div>
        <ul class="paginList">
      <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
        <li class="paginItem"><a href="javascript:;">1</a></li>
        <li class="paginItem current"><a href="javascript:;">2</a></li>
        <li class="paginItem"><a href="javascript:;">3</a></li>
        <li class="paginItem"><a href="javascript:;">4</a></li>
        <li class="paginItem"><a href="javascript:;">5</a></li>
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        <li class="paginItem"><a href="javascript:;">10</a></li>
        <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li> 
        </ul> -->
        
         <common:pageV2 url="wechatgoodlist.do" optimize="true" />
    </div>
    
   
    <div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="images/wechatmanage/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <input name="" type="button"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    
    </div>
    
    
    
    
    </div>
    
    <script type="text/javascript">
	$('.tablelist tbody tr:odd').addClass('odd');
	</script>

</body>

</html>
