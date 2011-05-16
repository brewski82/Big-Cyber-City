<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="editPhotos">
<h2>Add and delete your photos</h2>
<h4>JPEG photos only</h4>
<c:if test="${deleted}">
	<div id="alerts">Photo Deleted!</div>
</c:if> <c:if test="${added}">
	<div id="alerts">Photo Added!</div>
</c:if>
<c:if test="${changedDefault}">
	<div id="alerts">Default Picture Changed!</div>
</c:if>
 <c:if test="${showForm}">

We're sorry, but uploading new photos is temporarily disabled due to spam. 
<!--
 Choose a photo to upload
<form method="post" action="/editPhotos.htm" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit" value="Upload"/>
        </form>
-->
</c:if>

 <c:if test="${!showForm}">
<div id="max">
You can add up to 5 photos
</div>
</c:if>

<table>
	<c:forEach var="photo" items="${photos}">
		<tr>
			<td><img src=<c:out value="${photo.url}"/><c:out value="${photo.fileName}"/> /></td>
			<td><input type="button" value="Delete" onclick=deletePhoto('<c:out value="${photo.photoId}"/>') /><br />
			<br />
			<br />
			<c:if test="${!photo.displayPic}">
					<input type="button" value="Make Default" onclick=makeDefault('<c:out value="${photo.photoId}"/>') />
				</c:if></td>
		</tr>
	</c:forEach>

</table>

</div>