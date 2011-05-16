<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>

<%@ page session="true" %>
output <br> <br>
<security:authentication property="principal.username"/>