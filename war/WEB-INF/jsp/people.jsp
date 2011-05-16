<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="searchBox">
<select name="gender">
	<option>Gender</option>
	<option>Male</option>
	<option>Female</option>
</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Between Ages &nbsp;&nbsp;&nbsp; 
<select id="startAge">
	<option>--</option>
	<c:forEach var="i" begin="18" end="100">
		<option>
	  <c:out value="${i}" />
	  </option>
	</c:forEach>
</select> &nbsp;&nbsp;&nbsp;and &nbsp;&nbsp;&nbsp;
<select id="endAge">
	<option>--</option>
	<c:forEach var="i" begin="18" end="100">
		<option>
	  <c:out value="${i}" />
	  </option>
	</c:forEach>
</select> <br>
<br />
<select id="status">
	<option>Status</option>
	<option>Single</option>
	<option>Taken</option>
	<option>Married</option>
</select> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Located &nbsp;&nbsp;&nbsp; 
<select id="miles">
	<option>10</option>
	<option>50</option>
	<option>100</option>
	<option>200</option>
</select> &nbsp;&nbsp;&nbsp; miles from zip code &nbsp;&nbsp;&nbsp; 
<input type="text" name="zip" size=5 value="" /> &nbsp;&nbsp;&nbsp; 
<input type="hidden" name="pageNumber" value="12" />
<input value="Send"	onclick="send()" type="button"></div>

<div id="reply"></div>





