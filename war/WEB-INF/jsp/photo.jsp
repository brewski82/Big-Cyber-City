<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="photoTitle">
<a href=<c:out value="/profile.htm?id=${user.id}" /> >
<c:out value="${user.displayName}" /></a>'s Photo
</div>

<div id="photo">
<img src=<c:out value="${photoUrl}" /> />
</div>