<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common_head.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>账目结算</title>
    
    <script type="text/javascript">
    	Ext.onReady(function(){
    		new Ext.Button({
    			text: '期间结算',
    			width: 100,
    			renderTo: 'monthBtn',
    			handler: function(){
    				alert("期间结算");
    			}
    		});
    		
    		new Ext.form.DateField({
    			fieldLabel: '结算日期',
    			value: new Date(),
    			maxValue: new Date(),
    			format: 'Y-m-d',
    			width: 200,
    			labelAlign: 'right',
    			renderTo: 'monthDate'
    		});
    	});
    </script>
  </head>
  
  <body>
  	<div>
  		<div id="month">
  			<div id="monthDate" style="width: 300px; float: left;">
  				<div style="float: left; line-height: 22px; " >
  					<label>结算日期:&nbsp;</label>
  				</div>
  			</div>
  			<div id="monthBtn"></div>
  		</div>
  		
  		<div id="year">
  			<div id="yearDate" style="width: 300px; float: left;">
  				<div style="float: left; line-height: 22px; " >
  					<label>结算年度:&nbsp;</label>
  				</div>
  			</div>
  			<div id="yearBtn"></div>
  		</div>
  		
  		<div id="expense">
  			<div id="expenseDate" style="width: 300px; float: left;">
  				<div style="float: left; line-height: 22px; " >
  				</div>
  			</div>
  			<div id="expenseBtn"></div>
  		</div>
  	</div>
  
  	
  </body>
</html>
