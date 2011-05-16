<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page session="true"%>
<div id="editProfile">
<h2>Edit your <a href="/profile.htm?id=<c:out value="${user.id}" />">profile</a></h2>
<c:if test="${updated}">
<div id="alerts">
Profile Updated! <a href="/dashboard.htm">Back to Dashboard</a>
</div>
</c:if>

<form name="profile" method="post" action="/editProfile.htm">
<table>
	<tr>
		<td class="left">Name:</td>
		<td class="right"><input name="displayName" type="text"
			value="<c:out value="${user.displayName}" />" /></td>
	</tr>
	<tr>
		<td class="left">Gender:</td>
		<td class="right"><select name="gender">
			<option>Male</option>
			<option>Female</option>
		</select></td>
	</tr>
	<tr>
		<td class="left">Birthdate:</td>
<td class="right">
<select name="month">
	<c:forEach var="i" begin="1" end="12">
		<option>
	  <c:out value="${i}" />
	  </option>
	</c:forEach>
</select>
<select name="day">
	<c:forEach var="i" begin="1" end="31">
		<option>
	  <c:out value="${i}" />
	  </option>
	</c:forEach>
</select>
<select name="year">
	<c:forEach var="i" begin="1920" end="2009">
		<option>
	  <c:out value="${i}" />
	  </option>
	</c:forEach>
</select>

    </td>
	</tr>
	<tr>
		<td class="left">Location:</td>
		<td class="right"><input name="location" type="text"
			value="<c:out value="${user.location}" />" /></td>
	</tr>
	<tr>
		<td class="left">Relationship Status:</td>
		<td class="right"><select name="relationshipStatus">
			<option>Single</option>
			<option>Taken</option>
			<option>Married</option>
		</select></td>
	</tr>
	<tr>
		<td class="left">Zip (US only):</td>
		<td class="right"><input name="zip" type="text" size="5"
			value="<c:out value="${user.zip}" />" /></td>
	</tr>
	<tr>
		<td colspan="2">About me</td>
	</tr>
	<tr>
		<td colspan="2"><textarea name="about" rows="10" cols="75"><c:out
			value="${user.about}" /></textarea></td>
	</tr>
	<tr>
		<td colspan="2"><input Value="Save Changes" type="submit" /></td>
	</tr>
</table>
</form>

</div>

<script type="text/javascript">
dwr.util.setValue("gender", "<c:out value="${user.gender}" />");
dwr.util.setValue("relationshipStatus", "<c:out value="${user.relationshipStatus}" />");
dwr.util.setValue("day", "<c:out value="${day}" />");
dwr.util.setValue("month", "<c:out value="${month}" />");
dwr.util.setValue("year", "<c:out value="${year}" />");
</script>