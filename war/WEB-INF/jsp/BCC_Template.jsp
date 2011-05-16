<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<meta name="Description"
	content="A new social network where members own the site" />
<meta name="Keywords"
	content="social, network, social network, big cyber city, money, dating, local" />
<meta name="author" content="William Bruschi"/>
<link href="/static/css/main.css" rel="stylesheet" type="text/css" />
<tiles:useAttribute id="cssPages" name="css" classname="java.util.List" />
<c:forEach var="page" items="${cssPages}">
<link href="/static/css/<tiles:insertAttribute value="${page}" flush="true" />" rel="stylesheet" type="text/css" />
</c:forEach>
<tiles:useAttribute id="dwrFiles" name="dwr" classname="java.util.List" />
<c:forEach var="file" items="${dwrFiles}">
<script type='text/javascript' src='/dwr/interface/<tiles:insertAttribute value="${file}" flush="true" />'></script>
</c:forEach>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type='text/javascript' src='/static/js/main.js'></script>
<!--[if lt IE 8]>
<script src="http://ie7-js.googlecode.com/svn/version/2.0(beta3)/IE8.js" type="text/javascript"></script>
<![endif]-->
<tiles:useAttribute id="jsFiles" name="js" classname="java.util.List" />
<c:forEach var="file" items="${jsFiles}">
<script type='text/javascript' src='/static/js/<tiles:insertAttribute value="${file}" flush="true" />'></script>
</c:forEach>
<title><tiles:insertAttribute name="title" /></title>
</head>
<body>
<div class="outer">
<div class="header">
<div class="nav-box"><a href="/dashboard.htm">Dashboard</a> | <a href="/people.htm">People</a>
| <a href="/beta.jsp">Gallery</a> | <a href="/beta.jsp">Reports</a>
| <a href="/beta.jsp">Help</a>  

<security:authorize ifAnyGranted="ROLE_USER,ROLE_ADMINISTRATOR">
| <a href="/j_spring_security_logout">Logout</a>
</security:authorize>

<security:authorize ifNotGranted="ROLE_USER,ROLE_ADMINISTRATOR">
| <a href="/login.jsp">Login</a>
</security:authorize>



</div>
<span class="title"><a href="/" title="Big Cyber City">Big
Cyber City</a></span></div>

<tiles:insertAttribute name="content" />

<div class="footer">
<div id="footer"><a href="/dashboard.htm">Dashboard</a> | <a href="/people.htm">People</a> | 
	 <a href="/beta.jsp">Help</a> | <a href="/beta.jsp">Terms</a> | <a
	href="/beta.jsp">Privacy</a> | <a href="/contact.jsp">Contact</a>  <br />
&copy; Copyright 2008 Big Cyber City, All Rights Reserved <br />
<br />
</div>
</div>
</div>
<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-520832-2");
pageTracker._trackPageview();
} catch(err) {}</script>
</body>
</html>

