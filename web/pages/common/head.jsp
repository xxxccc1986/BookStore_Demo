<%--
  Created by IntelliJ IDEA.
  User: hcxs1986
  Date: 2022/3/26
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%-- 输出的内容是http://192.168.0.101:8080/Books/ --%>
<%
  String  basePath = request.getScheme()
          + "://"
          + request.getServerName()
          + ":"
          + request.getServerPort()
          + request.getContextPath()
          + "/";
    pageContext.setAttribute("basePath",basePath);
%>

<%--<%=basePath%>--%>

<!--base用于固定相对路径跳转的地址	-->
<base href="<%=basePath%>">
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript " src="static/script/jquery-1.7.2.js"></script>
