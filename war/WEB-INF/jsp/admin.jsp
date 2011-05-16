<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="admin">
<h2>Admin Page</h2>

<div id="alerts"></div>

<div id="deleteAccount">
		<h4>Delete an account</h4>
		<ul>
			<li>Account ID: <input name="accountId" type="text" /></li>		
		</ul>
		<input type="button" value="Delete" onClick="deleteAccount()" />
</div>



</div>