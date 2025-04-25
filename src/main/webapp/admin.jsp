<%@page import="com.mycompany.project.dao.CategoryDao"%>
<%@page import="com.mycompany.project.entities.User"%>
<%@page import="com.mycompany.project.entities.Category"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="com.mycompany.project.helper.FactoryProvider" %>
<%@page import="com.mycompany.project.helper.Helper" %>

<%
User user=(User)session.getAttribute("current-user");
if(user==null){
session.setAttribute("message", "you are not logged in !! login first");
response.sendRedirect("login.jsp");
return;
}

else{
if(user.getUserType().equals("normal")){
session.setAttribute("message", "you are not admin!!");
response.sendRedirect("login.jsp");
return;
}
}


%>


<%
CategoryDao cdao= new CategoryDao(FactoryProvider.getFactory());
List<Category> list=cdao.getCategories();

Map<String,Long> m= Helper.getCount(FactoryProvider.getFactory());

%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin-Panel</title>
        
        <%@include file="components/common_css_js.jsp"%>
        
    </head>
    <body>
        <%@include file="components/navbar.jsp"%>

        <div class="container admin">
            <div class="container-fluid mt-3">
                <%@include file="components/message.jsp" %>
            </div>
        <div class="row mt-3">
            
<!--            first column-->
            <div class="col-md-4">
<!--                first box-->
                <div class="card">
                    <div class="card-body text-center">
                        <div class="container">
                            <img style="max-width: 150px" class="image-fluid rounded-circle" src="img/user.png" alt="user_logo"/>
                        </div>
                        <h1> <%= m.get("userCount") %> </h1>
                        <h1 class="text-uppercase text-muted">Users</h1>
                    </div>
                </div>
            </div>
            

<!--            second column-->
            <div class="col-md-4">
<!--                second box-->
                <div class="card">
                    <div class="card-body text-center">
                         <div class="container">
                            <img style="max-width: 150px" class="image-fluid" src="img/category.png" alt="user_logo"/>
                        </div>
                        <h1> <%= list.size() %> </h1>
                        <h1 class="text-uppercase text-muted">Categories</h1>
                    </div>
                </div>
            </div>

<!--            third column-->
            <div class="col-md-4">
<!--                third box-->
                <div class="card">
                    <div class="card-body text-center">
                         <div class="container">
                            <img style="max-width: 150px" class="image-fluid " src="img/product.png" alt="user_logo"/>
                        </div>
                        <h1> <%= m.get("productCount") %> </h1>
                        <h1 class="text-uppercase text-muted">products</h1>
                    </div>
                </div>
            </div>


        </div>

            
<!--            second Row-->
<div class="row mt-3">
<!--    first col-->
    <div class="col-md-6">
        <div class="card" data-toggle="modal" data-target="#add-category-modal">
                    <div class="card-body text-center">
                         <div class="container">
                            <img style="max-width: 150px" class="image-fluid " src="img/add-category.png" alt="user_logo"/>
                        </div>
                        <h1>123</h1>
                        <p>click here to add new category</p>
                        <h1 class="text-uppercase text-muted">Add Category</h1>
                    </div>
                </div>
    </div>
<!--    second col-->
    <div class="col-md-6">
        <div class="card" data-toggle="modal" data-target="#add-product-modal">
                    <div class="card-body text-center">
                         <div class="container">
                            <img style="max-width: 150px" class="image-fluid " src="img/add-product.png" alt="user_logo"/>
                        </div>
                        <h1>123</h1>
                        <p>click here to add new products</p>
                        <h1 class="text-uppercase text-muted">Add Product</h1>
                    </div>
                </div>
    </div>
</div>
        </div>

        <%-- add category modal --%>
<!-- Modal -->
<div class="modal fade" id="add-category-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLabel">Fill Category Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>

      <div class="modal-body">
          <form action="ProductOperationServlet" method="post">
              <input type="hidden" name="operation" value="addCategory">
              <div class="form-group">
                  <input class="form-control" type="text" name="catTitle" placeholder="enter category Title" required>
              </div>
              
              <div class="form-group">
                  <textarea class="form-control" name="catDescription" style="height: 350px" placeholder="enter category description"></textarea>
              </div>
              

              <div class="container text-center">
                  <button class="btn btn-outline-success">Add Category</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
              

          </form>
        

      </div>
    </div>
  </div>
</div>
        <%-- end of modal add category --%>

<!--        add product modal-->
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="add-product-modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header custom-bg text-white">
        <h5 class="modal-title" id="exampleModalLabel">Fill Product Details</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <form action="ProductOperationServlet" method="post" enctype="multipart/form-data">
              <input type="hidden" name="operation" value="addProduct">
              <div class="form-group">
                  <input class="form-control" type="text" name="productName" placeholder="enter product name" required>
              </div>
              <div class="form-group">
                  <textarea class="form-control" name="productDescription" placeholder="enter product description" style="height: 200px" required></textarea>
              </div>


              <div class="form-group">
                  <input class="form-control" type="number" name="productPrice" placeholder="enter product price" required>
              </div>
              <div class="form-group">
                  <input class="form-control" type="number" name="productDiscount" placeholder="enter the discount on the product" required>
              </div>
              <div class="form-group">
                  <input class="form-control" type="number" name="productQuantity" placeholder="enter the Quantity of the product" required>
              </div>



               <!--              product category-->
                            <div class="form-group">
                                  <select name="catId" class="form-control" id="">

                <%
                for(Category c: list){
                %>
                                    <option value="<%= c.getCategoryId() %>"> <%= c.getCategoryTitle() %> </option>

<%
}
%>

                                </select>
                            </div>


              <!--              picture of product-->
              <div class="form-group">
                  <label for="pPic">
                      Select a Product Picture:
                  </label>
                  <br>
                  <input type="file" name="pPic" required>
              </div>
                      

 <div class="container text-center">
                  <button class="btn btn-outline-success">Add Product</button>
                  <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
              </div>
          </form>
      </div>
      
    </div>
  </div>
</div>
<!--end of add product modal-->


<%@include file="components/common_modals.jsp"%>
    </body>
</html>
