<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

  <c:if test="${param.login_error == 1}">
    <div id="alerts">Incorrect username or password!</div>
  </c:if>

<div class="login">
Please log in to continue
<form method="POST" action="j_spring_security_check">
<b>Username: </b><input type="text" name="j_username"><br>
<b>Password: </b><input type="password" name="j_password"><br>
<input type="submit" value="Login">
</form>
</div>