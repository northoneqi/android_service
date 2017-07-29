<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'chart.jsp' starting page</title>
    
    <script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="echart/echarts-plain-map.js"></script>
	
	<script type="text/javascript">
		$(function(){
			 var myChart2 = echarts.init(document.getElementById('container'));
		     myChart2.setOption({
		        tooltip : {
		            trigger: 'item',
		            formatter: '{b}'
		        },
		        series : [{
	                name: '中国',
	                type: 'map',
	                mapType: 'china',
	                selectedMode : 'multiple',
	                itemStyle:{
	                    normal:{label:{show:true}},
	                    emphasis:{label:{show:true}}
	                },
	                data:[
	                    {name:'广东',selected:true}
	                ]
		        }]
		    });
		});
	</script>
  </head>
  
  <body>
  	  <div id="container" style="height:500px;border:1px solid #ccc;padding:10px;"></div>
  </body>
</html>
