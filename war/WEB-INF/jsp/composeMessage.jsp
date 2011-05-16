<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="true"%>
<br /><br /><a href="/inbox.htm">Go to your inbox</a><br /><br />
<div id="composeMessage">

<h2> Send a message to <a href="/profile.htm?id=<c:out value="${user.id}" />" ><c:out value="${user.displayName}" /></a></h2>

<form name="message" method="post" action="/composeMessage.htm">
<input type="hidden" name="toId" value=<c:out value="${user.id}" /> />


<div id="subject">
Subject: <input type="text" name="subject" size="40" />
</div>


<div id="body">
<textarea name="body" rows="20" cols="70">


<c:if test="${reply}">
<c:out value="ORIGINAL MESSAGE: ${message}" />
</c:if>
</textarea>
</div>

<input type="submit" value="Send" />
</form>


</div>