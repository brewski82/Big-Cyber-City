<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div class="description"><a
	href="/profile.htm?id=<c:out value="${user.id}" />"> <c:out
	value="${user.displayName}" /></a>'s <c:out value="${page}" /></div>
<div id="sub"><c:if test="${viewYourComments}">
	<a href="/postedComments.htm">View your posted comments</a>
</c:if> <c:if test="${viewComments}">
	<a href="/comments.htm?id=<c:out value="${user.id}" />">View your
	comments</a>
</c:if> <c:if test="${viewOutbox}">
	<a href="/outbox.htm">Go to your outbox</a>
</c:if> <c:if test="${viewInbox}">
	<a href="/inbox.htm">Go to your inbox</a>
</c:if></div>
<input type="hidden" name="userId" value=<c:out value="${user.id}" /> />
<input type="hidden" name="pageNumber" value=0 />

<div id="reply"></div>