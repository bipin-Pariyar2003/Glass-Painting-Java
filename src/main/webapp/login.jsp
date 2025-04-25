

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Login -GlassPainting</title>
         <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
    <%@include file="components/navbar.jsp"%>
        <div class="container">
            <div class="row">
                <div class="col-md-4 offset-md-4">
                    
                <div class="card">
                
                    <div class="card-header custom-bg text-white">
                        <h3>Login Here</h3>
                    </div>
                    <div class="card-body">

                    <%@include file="components/message.jsp"%>

                        <form action="LoginServlet" method="post">
  <div class="form-group">
    <label for="exampleInputEmail1">Email address</label>
    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Enter email">
    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">Password</label>
    <input name="password" type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
  </div>
                            <a href="register.jsp" class="text-center d-block mb-2">If not registered click here!!</a>
  
                            <div class="container text-center">
                               
                                <button type="submit" class="btn btn-primary border-0 custom-bg">Submit</button>
                                <button type="reset" class="btn btn-primary border-0 custom-bg">Reset</button>
                            </div>
</form>
                    </div>


                </div>
            </div>
        </div>
    </body>
</html>
