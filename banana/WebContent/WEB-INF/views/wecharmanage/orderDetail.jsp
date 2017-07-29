<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单详细</title>
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

});
</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">订单详细</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <!-- <div class="tools">
    
    	<ul class="toolbar">
        <li class="click"><span><img src="images/wechatmanage/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/wechatmanage/t02.png" /></span>修改</li>
        <li><span><img src="images/wechatmanage/t03.png" /></span>删除</li>
        <li><span><img src="images/wechatmanage/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="images/wechatmanage/t05.png" /></span>设置</li>
        </ul>
    
    </div> -->
		<div class="topdiv">
			
			<div>支付方式：<c:if test="${details.PayType==2 }">货到付款 : ${total}</c:if>
			<c:if test="${details.PayType==8 }">亿家卡支付 : ${total}</c:if>
			<c:if test="${details.PayType==12 }">微信支付 : ${total}</c:if></div>
			<div>客户姓名：${details.Consignee }</div>
			<div>订单编码：${details.WeixinOrderCode }</div>
			<div style="clear:both"></div>
			<div>联系电话：${details.Mobile }</div>
			<div>订单时间：${details.OrderTime }</div>
			<div style="clear:both"></div>
			<div>送货地址：${address }</div>
			<div>订单状态：${paraName}</div>
		</div>
		<table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>商品编码<i class="sort"><img src="images/wechatmanage/px.gif" /></i></th>
        <th>商品名称</th>
        <th>销售单位</th>
        <th>销售单价</th>
        <th>订货数量</th>
        <th>订货金额</th>
        
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orderDetails}" var="sr">
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>${sr.OrderCode }</td>
        <td>${sr.ProName }</td>
        <td>${sr.ParaName }</td>
        <td>${sr.BuyPrice }</td>
        <td>${sr.ProNum }</td>
        <td>${sr.Subtotal }</td>
        
        <!-- <td><a href="#" class="tablelink">查看</a>     <a href="#" class="tablelink"> 删除</a></td> -->
        </tr> 
        </c:forEach>
             
        </tbody>
    </table>
    
   
    <!-- <div class="pagin">
    	<div class="message">当前显示第&nbsp;<i class="blue">1&nbsp;</i>页</div>
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
        </ul>
    </div> -->
    
    
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
