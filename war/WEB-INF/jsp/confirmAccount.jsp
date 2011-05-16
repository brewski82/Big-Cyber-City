<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="confirmAccount">
<h2>Enter your confirmation number</h2>


<c:if test="${error}">
<div id="alerts">

Wrong confirmation number.  Please try again.
</div>
				
</c:if>

<c:if test="${success}">
<div id="alerts">

Success! <a href="/login.jsp">Click here</a> to log in.
</div>
				
</c:if>
<c:out value="${num}" />
<div id="commentForm">

<form name="accountNumber" method="post" action="/confirmAccount.htm">

<input type=text name="confirmNumber" />
<br>
<br>
<input value="Submit" type="submit">

</form>

</div>


</div>