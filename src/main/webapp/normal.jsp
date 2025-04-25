<%@page import="com.mycompany.project.entities.User"%>

<%
User user= (User)session.getAttribute("current-user");
if(user==null){
session.setAttribute("message", "you are not logged in!! login first");
response.sendRedirect("login.jsp");
return;
}
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>NormalUser-panel</title>
        <%@include file="components/common_css_js.jsp"%>
    </head>
    <body>
       <%@include file="components/navbar.jsp"%>
        <h1>This is Normal User Panel</h1>
    </body>
</html>
