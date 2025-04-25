<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New User</title>
 <%@include file="components/common_css_js.jsp" %>


  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <style>

    body {
      background-color: #f4f4f4;
      padding: 0px;
    }

    .custom-form-container {
      max-width: 400px;
      margin: 0 auto;
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }
  </style>
</head>
<body>
<%@include file="components/navbar.jsp"%>
<form action="RegisterServlet" method="post">
 <div class="container">
    <div class="row">
      <div class="col-md-12">
        <div class="custom-form-container">

          <h2 class="text-center">Sign Up Here !!!</h2>
           <%@include file="components/message.jsp"%>
          <br>

          <form>

            <div class="form-group">
              <label for="username">Username:</label>
              <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="form-group">
              <label for="useremail">Email:</label>
              <input type="email" class="form-control" id="useremail" name="useremail" required>
            </div>

            <div class="form-group">
              <label for="userpassword">Password:</label>
              <input type="password" class="form-control" id="userpassword" name="userpassword" required>
            </div>

            <div class="form-group">
              <label for="userphone">Phone:</label>
              <input type="tel" class="form-control" id="userphone" name="userphone" required>
            </div>

            <div class="form-group">
              <label for="useraddress">Address:</label>
              <input type="text" class="form-control" id="useraddress" name="useraddress" required>
            </div>

            <button type="submit" class="btn btn-success btn-block">Register</button>
            <button type="reset" class="btn btn-danger btn-block">Reset</button>
          </form>
        </div>
      </div>
    </div>
  </div>

  
</form>
</div>

</body>
</html>
