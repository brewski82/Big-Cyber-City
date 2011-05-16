<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div class="wrap">
	<div id="displayName">
		<h1><c:out value="${user.displayName}" /></h1>
	</div>
	<div id="photos">
		<div id="mainPhoto">
			<img id="bigpicture" src=<c:out value="${user.displayPicPath}"/><c:out value="${user.displayPic}"/> />
		</div>
		<div id="photoList">
			<c:forEach var="photo" items="${photos}">
				<a href=<c:out value="/viewPhoto.htm?id=${photo.photoId}" /> >
				<img width=65 height=50 src=<c:out value="${photo.url}"/><c:out value="${photo.fileName}"/> 
					onMouseOver=changePic('<c:out value="${photo.url}"/><c:out value="${photo.fileName}"/>') />
				</a>
			</c:forEach>
		</div>
	</div>
	<div id="info">
		<table>
			<tr>
				<td><c:out value="${user.age}" /> yr old <c:out
					value="${user.gender}" /></td>
			</tr>
			<tr>
				<td><c:out value="${user.location}" /></td>
			</tr>
			<tr>
				<td><c:out value="${user.relationshipStatus}" /></td>
			</tr>
			<tr>
				<td><a href="#" onClick=reqFriend(<c:out value="${user.id}" />)>Add to friends</a></td>
			</tr>
			<tr>
				<td><a href="/composeMessage.htm?to=<c:out value="${user.id}" />">Send Message</a></td>
			</tr>
			<tr>
				<td><a href="#">Favorites</a></td>
			</tr>
		</table>	
	</div>
	<div id="about">
		<p><c:out value="${user.about}" /></p>
	</div>
</div>

<div class="wrap">
	<div id="friends">		
		<h2>Friends</h2>
		<div class="viewAll"><a href="/friends.htm?id=<c:out value="${user.id}" />" >View All</a></div>
		<table>
			<% boolean newRow = true;%>
			<c:forEach var="friend" items="${friends}">
				<% if(newRow) { %>
					<tr>
				<% } %>
				<td>
					<a href="<c:out value="/profile.htm?id=${friend.userId}" />" >
						<img src=<c:out value="${friend.photo}" /> />
					</a>
				</td>	
				<% if(!newRow) { %>
					</tr>
				<% } 
					newRow = !newRow;%>
			</c:forEach>			
			<% if(newRow) { %></tr><%} %>
		</table>		
	</div>
	<div id="comments">
		<h2>Comments</h2>
		<div class="viewAll"><a href="/comments.htm?id=<c:out value="${user.id}" />" >View All</a> ---  
		<a href="/postComment.htm?id=<c:out value="${user.id}" />" >Post Comment</a></div>
		<table valign="top">
			<c:forEach var="comment" items="${comments}">
				<tr>
					<td>
						<a href="<c:out value="/profile.htm?id=${comment.userId}" />" >
							<img src=<c:out value="${comment.photo}"/> />
						</a>
					</td>
					<td>
						<span id="commentAuthor">
							<a href="<c:out value="/profile.htm?id=${comment.userId}" />" >
								<c:out value="${comment.userName}" />
							</a>
						</span>
						<br />
						<span id="commentDate"><c:out value="${comment.info}" /></span>
						<br />
						<c:out escapeXml="false" value="${comment.contents}"  />
						<c:if test="${comment.canDelete}">
							<br /><a href="#" onClick=deleteComment(<c:out value="${comment.id}" />)>Delete Comment </a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
