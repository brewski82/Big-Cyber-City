<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="true"%>

<div id="account">
<h2>Account Settings</h2>

<div id="alerts"></div>

<div id="changeEmail">
		<h4>Change Email Address</h4>
		<ul>
			<li>Email: <input name="email" type="text" /></li>
			<li>Password: <input name="password" type="password" /></li>			
		</ul>
		<input type="button" value="Change" onClick="changeEmail()" />
</div>

<div id="changePassword">
		<h4>Change Password</h4>
		<ul>
			<li>Old Password: <input name="oldPassword" type="password" /></li>
			<li>New Password: <input name="newPassword" type="password" /></li>
			<li>Retype New Password: <input name="newPassword2" type="password" /></li>			
		</ul>
		<input type="button" value="Change" onClick="changePassword()" />
</div>

<div id="deleteAccount">
<h4>Delete Your Big Cyber City Account</h4>
<input type="button" value="Delete Account" onClick="deleteAccount()" />
</div>


</div>