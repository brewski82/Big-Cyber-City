<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<h2> Create a new Account </h2>

<div id="alerts"></div>

<div id="register">

Email: <input name="email" type="text" /
<br /><br /><br />

Password: <input name="password" type="password" />
<br /><br /><br />
<input type="button" value="Submit" onClick="createAccount()" />
</div>