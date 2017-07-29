<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="common" uri="http://javass.cn/common/" %>
<!doctype html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>订单列表</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/My97DatePicker/WdatePicker.js"></script>
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
  $(".paginItem").click(function(){
	  	/* alert("21313123213");
	  	console.log($("#formsearch").serialize());
		 */
		 $("#formsearch").submit();
		
	  });
  
  $(".resetclick").click(function(){
		 $("#beginTime").val("");
		 $("#EndTime").val("");
		 $("#orderCode").val("");
		 $("select").val("0");
	  });

});

</script>


</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">订单列表</a></li>
    <li><a href="#">基本内容</a></li>
    </ul>
    </div>
    
    <div class="rightinfo"> 
    <form action="<c:url value='orderList.do'/>"  id="formsearch" name="findForm">
    <div class="searchgood">
    <table>
    	<tr>
    		<td>起始时间：<input class="Wdate" value="${filter.stratDate}" type="text" id="beginTime" name="stratDate" style="width: 180px;height:25px;"
                                              onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /> </td>
    		<td>结束时间：<input class="Wdate" value="${filter.endDate}" type="text" id="EndTime" name="endDate" style="width: 180px;height:25px;"
                                              onFocus="WdatePicker({isShowClear:true,readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" /></td>
    	<td>订单编号：<input type="text" value="${filter.orderCode}" id="orderCode" name="orderCode"/> </td>
    	<td>支付方式:
    	<select name="payType">
    	<option value="0" disabled selected="selected">请选择</option>
    	<option value="2" <c:if test="${filter.payType==2}">selected</c:if> >货到支付</option>
    	<option value="12" <c:if test="${filter.payType==12}">selected</c:if> >微信支付</option>
    	<option value="8" <c:if test="${filter.payType==8}">selected</c:if> >亿家卡支付</option>
    	</select> </td>
    	
    	</tr>
     </table>
	</div>
	</form>
	<div class="tools">
    
    	<!-- <ul class="toolbar">
        <li class="click"><span><img src="images/wechatmanage/t01.png" /></span>添加</li>
        <li class="click"><span><img src="images/wechatmanage/t02.png" /></span>修改</li>
        <li><span><img src="images/wechatmanage/t03.png" /></span>删除</li>
        <li><span><img src="images/wechatmanage/t04.png" /></span>统计</li>
        </ul> -->
        
        <ul class="toolbar0">
        <li class="resetclick" ><span><img src="images/wechatmanage/t02.png" /></span>重置</li>
        </ul>
        <ul class="toolbar1">
        <li class="searchclick" ><span><img src="images/wechatmanage/t05.png" /></span>查询</li>
        </ul>
    
    </div>
    <table class="tablelist">
    	<thead>
    	<tr>
        <th><input name="" type="checkbox" value="" checked="checked"/></th>
        <th>订单编号<i class="sort"><img src="images/wechatmanage/px.gif" /></i></th>
        <th>客户名称</th>
        <th>客户地址</th>
        <th>客户电话</th>
        <th>订单金额</th>
        <th>下单时间</th>
        <th>支付方式</th>
        <th>订单类型</th>
       <!--  <th>操作状态</th> -->
        <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.items}" var="sr" varStatus="status">
        <tr>
        <td><input name="" type="checkbox" value="" /></td>
        <td>${sr.WeixinOrderCode }</td>
        <td>${sr.Consignee }</td>
        <td>${sr.Address }</td>
        <td>${sr.Mobile  }</td>
        <td>${sr.total }</td>
        <td>${sr.OrderTime }</td>
        <c:if test="${sr.PayType==2 }"><td>货到付款</td></c:if>
        <c:if test="${sr.PayType==8 }"><td>亿家卡支付</td></c:if>
        <c:if test="${sr.PayType==12 }"><td>微信支付</td></c:if>
        <c:if test="${sr.OrderType==5 }"><td>微信订单</td></c:if>
        <c:if test="${sr.OrderType==8 }"><td>众筹订单</td></c:if>
        <!-- <td>未检查</td> -->
        <td><a href="/jhyj-wechat-admin/orderDetail.do?weixinOrderCode=${sr.WeixinOrderCode }&address=${sr.Address }&paraName=${sr.ParaName}&total=${sr.total }" class="tablelink">查看</a></td> 
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
        <common:pageV2 url="orderList.do" optimize="true" />
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
