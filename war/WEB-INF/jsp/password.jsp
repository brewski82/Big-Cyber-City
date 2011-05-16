
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="pass">
<h2>Get Your Password</h2>

<div id="alerts"></div>

<div id="sendPassword">
		<h4>Enter your email and we will send you your password</h4>
		<ul>
			<li>Email: <input name="email" type="text" /></li>		
		</ul>
		<input type="button" value="Send" onClick="sendPass()" />
</div>

</div>