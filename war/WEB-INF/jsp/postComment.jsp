<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="true"%>

<div id="postComment">
<h2>Post a comment for <a
	href="/profile.htm?id=<c:out value="${user.id}" />"><c:out
	value="${user.displayName}" /></a></h2>


<c:if test="${error}">
<div id="alerts">

You must be friends with this person to post a comment!
</div>
				
</c:if>
<div id="commentForm">

<form name="message" method="post" action="/postComment.htm">
<input type="hidden" name="toId" value=<c:out value="${user.id}" /> />

<textarea name="comment" rows="10" cols="50"></textarea>
<br>
<br>
<input value="Send" type="submit">

</form>

</div>


</div>