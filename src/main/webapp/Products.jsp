<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Amazon Market Insights</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <h1>Products</h1>
    <table border="1">
        <tr>
            <th>Product ID</th>
            <th>Title</th>
            <th>Image URL</th>
            <th>Product URL</th>
            <th>Stars</th>
            <th>Reviews</th>
            <th>Price</th>
            <th>Listed Price</th>
            <th>Category ID</th>
            <th>Best Seller</th>
            <th>Number of Views</th>
            <th>Likes</th>
            <th>Dislikes</th>
            <th>Action</th>
        </tr>

        <c:forEach var="product" items="${products}">
            <tr>
                <td>${product.productId}</td>
                <td>${product.title}</td>
                <td><img src="${product.imageUrl}" alt="Product Image" width="200" height="200"></td>
                <td><a href="${product.productUrl}" target="_blank">Visit</a></td>
                <td>${product.stars}</td>
                <td>${product.reviews}</td>
                <td>${product.price}</td>
                <td>${product.listedPrice}</td>
                <td>${product.categoryId}</td>
                <td>${product.bestSeller}</td>
                <td>${product.numViews}</td>
                <td>${product.likes}</td>
                <td>${product.dislikes}</td>
                <td>
                    <!-- Update form -->
                    <form action="products" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="productId" value="${product.productId}">
                        <input type="text" name="newTitle" placeholder="New Title">
                        <button type="submit">Update</button>
                    </form>
                    
                    <!-- Delete form -->
                    <form action="products" method="post">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="productId" value="${product.productId}">
                        <button type="submit">Delete</button>
                    </form>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td>541804</td>
            <td>Standing Desk</td>
            <td><img src="https://m.media-amazon.com/images/I/71rRNGs-+KL._AC_SY300_SX300_.jpg" alt="Sample Product Image" width="200" height="200"></td>
            <td><a href="https://www.amazon.com/Claiks-Electric-Standing-Adjustable-Height/dp/B0BZ7GXM4M/ref=sr_1_5?crid=254XUXPBYLL8F&dib=eyJ2IjoiMSJ9.McsR5_q89s8TWG_kuUKRUKk-8ZPaLbPxQFf-4TGquCigJqaO_OkeOOa3uMtwbM47elUH0IWwNPj8mXxFi0aqfQss0of554RmPFhE-xU-Op9AZoquDT0eq_Ry1touU1OhnThoDSJCV7qPo12rKN6Qt0SpnCfemLc-4SGmOmlgHrd56e0CTjWyHx3vt0y-M5XzPH-pL2NcljKEUr9GbYWIRMy7OhqbdQgVORMLwPFsnY5CX78_OdWYB3O9MDql298qIhAXDIFXpoYKpBwCa7jWdAPxq_svFWvOeWna39e9hIg.WYHwPzZKU9IhWE1rdxhnL5hx5v6DrOQoIu3sKSzy5Ow&dib_tag=se&keywords=standing&qid=1713225487&sprefix=standing+%2Caps%2C148&sr=8-5" target="_blank">Visit</a></td>
            <td>4.6</td>
            <td>1680</td>
            <td>119.99</td>
            <td>119.99</td>
            <td>1</td>
            <td>True</td>
            <td>5000</td>
            <td>200</td>
            <td>20</td>
            <td>
                <form action="products" method="post">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="productId" value="541804">
                    <input type="text" name="newTitle" placeholder="New Title">
                    <button type="submit">Update</button>
                </form>
                
                <form action="products" method="post">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="productId" value="541804">
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
    </table>
    
<div>
    <h2>Create Product</h2>
    <form action="products" method="post">
        <input type="hidden" name="action" value="create">
        <div class="form-group">
            <label for="productId">Product ID:</label>
            <input type="text" class="form-control" id="productId" name="productId">
        </div>
        <div class="form-group">
            <label for="title">Title:</label>
            <input type="text" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="imageUrl">Image URL:</label>
            <input type="text" class="form-control" id="imageUrl" name="imageUrl">
        </div>
        <div class="form-group">
            <label for="productUrl">Product URL:</label>
            <input type="text" class="form-control" id="productUrl" name="productUrl">
        </div>
        <div class="form-group">
            <label for="stars">Stars:</label>
            <input type="number" step="0.1" class="form-control" id="stars" name="stars">
        </div>
        <div class="form-group">
            <label for="reviews">Reviews:</label>
            <input type="number" class="form-control" id="reviews" name="reviews">
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price">
        </div>
        <div class="form-group">
            <label for="listedPrice">Listed Price:</label>
            <input type="number" step="0.01" class="form-control" id="listedPrice" name="listedPrice">
        </div>
        <div class="form-group">
            <label for="categoryId">Category ID:</label>
            <input type="number" class="form-control" id="categoryId" name="categoryId">
        </div>
        <div class="form-group">
            <label for="bestSeller">Best Seller:</label>
            <select class="form-control" id="bestSeller" name="bestSeller">
                <option value="true">True</option>
                <option value="false">False</option>
            </select>
        </div>
        <div class="form-group">
            <label for="numViews">Number of Views:</label>
            <input type="number" class="form-control" id="numViews" name="numViews">
        </div>
        <div class="form-group">
            <label for="likes">Likes:</label>
            <input type="number" class="form-control" id="likes" name="likes">
        </div>
        <div class="form-group">
            <label for="dislikes">Dislikes:</label>
            <input type="number" class="form-control" id="dislikes" name="dislikes">
        </div>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>

    
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
