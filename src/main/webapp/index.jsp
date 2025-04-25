

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.project.helper.FactoryProvider" %>
<%@ page import="com.mycompany.project.dao.ProductDao" %>
<%@ page import="com.mycompany.project.entities.Product" %>
<%@ page import="com.mycompany.project.dao.CategoryDao" %>
<%@ page import="com.mycompany.project.entities.Category" %>
<%@ page import="com.mycompany.project.helper.Helper" %>
<%@ page import="java.util.*" %>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ecommerce-Home</title>

        <%@include file="components/common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="components/navbar.jsp"%>

        <div class="container-fluid">

            <div class="row mt-3 mx-2">

                <%
                
                String cat=request.getParameter("category");  
            
                
                ProductDao dao= new ProductDao(FactoryProvider.getFactory());
                List<Product> list=null;
               
            
               if(cat==null || cat.trim().equals("all")){
                list=dao.getAllProducts();
                    }
                    else{
                    int cid=Integer.parseInt(cat.trim());
                    list=dao.getAllProductsById(cid);
                    }
            
        
                CategoryDao cdao= new CategoryDao(FactoryProvider.getFactory());
                List<Category> clist=cdao.getCategories();
                %>

                <!--show categories-->
                <div class="col-md-2">
                    <div class="list-group mt-4">
                        <a href="index.jsp?category=all" class="list-group-item list-group-item-action active" >
                            All Products
                        </a>



                        <%
                        for(Category c:clist){
                        %>
                        <a href="index.jsp?category=<%= c.getCategoryId() %>" class="list-group-item list-group-item-action"> <%= c.getCategoryTitle()%> </a>


                        <%
                    
                            }
                        %>
                    </div>
                </div>

                <!--show products-->
                <div class="col-md-10">


                    <!--                ROW-->
                    <div class="row mt-4">

                        <!--    COL 12 grid-->
                        <div class="col-md-12">

                            <div class="card-columns">

                                <!--        traversing products-->

                                <%
                            
                                for(Product p:list){
                            
                                
                                %>

                                    <!--product card-->
                                <div class="card product-card">

                                    <div class="container text-center">
                                        <img class="card-img-top m-2" style="max-height: 320px; max-width: 100%; width:auto;" src="img/products/<%= p.getPPhoto()%>" alt="Image">
                                    </div>

                                    <div class="card-body">

                                        <h5 class="card-title">
                                            <%= p.getPName() %>
                                        </h5>

                                        <p class="card-text">
                                            <%= Helper.get10Words(p.getPDesc()) %>
                                        </p>
                                    </div>

                                    <div class="card-footer text-center">
                                        <button class="btn custom-bg text-white" onclick="add_to_cart(<%= p.getPId() %>, '<%= p.getPName() %>', <%= p.getPriceAfterDiscount() %>)">Add to Cart</button>
                                        <button class="btn btn-outline-success"> &#8360; <%= p.getPriceAfterDiscount()%>/- <span class="text-secondary discount-label"> &#8360; <%= p.getPPrice() %>, <%= p.getPDiscount()%>% off</span></button>
                                    </div>
                                </div>

                                <%
                                    }
                                    if(list.size()==0){
                                    out.println("<h3>No Product in this Category</h3>");
    }
                                %>

                            </div>

                        </div>

                    </div>
                </div>
            </div>
        </div>

        <%@include file="components/common_modals.jsp"%>
    </body>
</html>
