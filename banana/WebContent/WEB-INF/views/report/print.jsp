<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8"%>
<%@ page language="java" import="com.ht.sys.bean.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
request.setAttribute("basePath", basePath);
request.setAttribute("userTheme", "gray");

SysUser user = (SysUser)request.getSession().getAttribute("loginUser");

if(user == null){
	response.sendRedirect(basePath);
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
<!--
html, body { height: 100%; } 
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
}
-->
</style>

<%
	String number = request.getParameter("number");
	String jaspername = request.getParameter("name");
	String projectName=request.getParameter("projectName");
	if(projectName!=null)projectName=java.net.URLEncoder.encode(projectName,"utf-8");
	//String projectName=java.net.URLEncoder.encode((String)request.getAttribute("projectName"),"utf-8");
	//String projectStatus=java.net.URLEncoder.encode((String)request.getAttribute("projectStatus"),"utf-8");
	String projectStatus=request.getParameter("projectStatus");
	if(projectStatus!=null)projectStatus=java.net.URLEncoder.encode(projectStatus,"utf-8");
	//String peopleName=java.net.URLEncoder.encode((String)request.getAttribute("peopleName"),"utf-8");
	String peopleName=request.getParameter("peopleName");
	if(peopleName!=null)peopleName=java.net.URLEncoder.encode(peopleName,"utf-8");
	System.out.println(projectStatus);
%>


</head>
<body>
<DIV align="center" style="height: 100%;">
<SCRIPT LANGUAGE="JavaScript"> 
var javawsInstalled = false; 
var isIE = "false"; 
if (navigator.mimeTypes && navigator.mimeTypes.length) { 
   x = navigator.mimeTypes['application/x-java-jnlp-file']; 
   if (x) { 
      javawsInstalled = 1;
   } 
} else { isIE = "true"; } 
</SCRIPT> 

<SCRIPT LANGUAGE="VBScript">
on error resume next
If isIE = "true" Then
  If Not(IsObject(CreateObject("JavaWebStart.isInstalled"))) Then
     javawsInstalled = false
  Else
     javawsInstalled = true
  End If 
End If
</SCRIPT> 

<SCRIPT LANGUAGE="JavaScript" charset="utf-8">
		document.write("<APPLET  CODE = \"EmbeddedViewerApplet.class\" CODEBASE = \"${basePath}/applets\" ARCHIVE = \"jasperreports-hxast-applet.jar, jasperreports-3.1.2-applet.jar,commons-logging-1.0.2.jar,commons-collections-2.1.jar");
		document.write("\" WIDTH = \"100%\" HEIGHT = \"100%\">");
		document.write(" <PARAM NAME = CODE VALUE = \"EmbeddedViewerApplet.class\" >");
		document.write("				<PARAM NAME=ARCHIVE");
		document.write("					VALUE=\"jasperreports-hxast-applet.jar, jasperreports-3.1.2-applet.jar,commons-logging-1.0.2.jar,commons-collections-2.1.jar");
		document.write("							\">");
		document.write("");
		document.write("				<PARAM NAME=\"type\" VALUE=\"application/x-java-applet;version=1.2.2\">");
		document.write("    <PARAM NAME=\"scriptable\" VALUE=\"false\">");
		document.write("    <PARAM NAME = \"REPORT_URL\" VALUE =\"${basePath}/report/applet/appletPrint.do?jaspername=<%=jaspername%>&number=<%=number%>&categoryCode=${reportBean.categoryCode}&startDate=${reportBean.startDate}&projectName=<%=projectName%>&endDate=${reportBean.endDate}&budgetDepartmentCode=${reportBean.budgetDepartmentCode}&departmentCode=${reportBean.departmentCode}&peopleName=<%=peopleName%>&projectStatus=<%=projectStatus%>\">");
		document.write("</APPLET>");
</SCRIPT>
</body>
</html>