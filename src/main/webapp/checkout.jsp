<%@page import="com.mycompany.project.helper.FactoryProvider" %>
<%@page import="com.mycompany.project.entities.User"%>

<%
User user=(User)session.getAttribute("current-user");
if(user==null){
session.setAttribute("message", "you are not logged in !! login first to access checkout page");
response.sendRedirect("login.jsp");
return;
}
else{


}
 %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Check Out Page</title>
<%@include file="components/common_css_js.jsp"%>
</head>
<body>
 <%@include file="components/navbar.jsp"%>

<div class="container">
<div class="row mt-5">
<div class="col-md-6">
<%-- card --%>
<div class="card">
<div class="card-body">
<h3 class="text-center mb-3"> Your Selected Items </h3>
<div class="cart-body">
</div>
</div>
    
    

</div>


</div>

<div class="col-md-6">
<%-- form details --%>

<div class="card">
<div class="card-body">
<h3 class="text-center mb-3"> Your Details for Order </h3>

<form action="OrderServlet" method="POST">

<div class="form-group">
    <label for="exampleInputEmail1">Your Name</label>
    <input value="<%= user.getUserName()%>" type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
  </div>

  <div class="form-group">
      <label for="exampleInputEmail1">Email address</label>
      <input value="<%= user.getUserEmail() %>" type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
      <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
    </div>

    <div class="form-group">
        <label for="exampleInputEmail1">Phone Number</label>
        <input value="<%= user.getUserPhone() %>" type="number" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter Number">
        <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
      </div>

   <div class="form-group">
      <label for="exampleFormControlTextarea1">Your Shipping Address</label>

      <textarea value="<%= user.getUserAddress()%>" class="form-control" id="exampleFormControlTextarea1" placeholder="enter your address" rows="3"></textarea>


    </div>

<div class="container text-center">
 <button class="btn btn-outline-success"> Order Now </button>
 <button class="btn btn-outline-primary" onclick="index.jsp"> Continue Shopping </button>

 </div>
</form>


</div>

</div>

</div>
</div>

<%@include file="components/common_modals.jsp"%>
</body>
</html>
