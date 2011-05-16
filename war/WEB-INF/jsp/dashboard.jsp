<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="dashboard">
<h1>Welcome <c:out value="${user.displayName}" /></h1>


<c:forEach var="alert" items="${alerts}">

	<div id="alerts">
	<div id=<c:out value="${alert.id}" />><c:if
		test="${alert.type == 'Friend'}">
		<a href="/profile.htm?id=<c:out value="${alert.fromId}" />"><c:out
			value="${alert.fromName}" /></a> wants to be your friend.
    			<a href="#" onClick=acceptFriend(<c:out value="${alert.id}" />)>Accept</a> or <a
			href="#" onClick=denyFriend(<c:out value="${alert.id}" />)> deny</a>?
 			 </c:if> <c:if test="${alert.type == 'Mail'}">
				You have new Mail! <a href="#" onClick=readMail(<c:out value="${alert.id}" />)>Click here</a> to read
			</c:if>
			<c:if test="${alert.type == 'Comment'}">
				You have new Comments! <a href="#" onClick="readComments(<c:out value='${alert.id}' />, <c:out value='${user.id}' /> )">Click here</a> to read
			</c:if>			
			
			</div>
	</div>
</c:forEach>



<div id="menu">
<ul>
	<li><a href="/editProfile.htm">Profile</a></li>
	<li><a href="/editPhotos.htm">Photos</a></li>
	<li><a href="/inbox.htm">Mail</a></li>
	<li><a href="/friends.htm?id=<c:out value="${user.id}" />">Friends</a></li>
	<li><a href="/comments.htm?id=<c:out value="${user.id}" />">Comments</a></li>
	<li><a href="/account.htm">Account</a></li>
</ul>
</div>

</div>