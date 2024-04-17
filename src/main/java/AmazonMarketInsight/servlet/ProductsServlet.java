package AmazonMarketInsight.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AmazonMarketInsight.dal.ProductsDao;
import AmazonMarketInsight.model.Products;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {

    private ProductsDao productsDao;

    public void init() throws ServletException {
        productsDao = ProductsDao.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Products> productList = productsDao.getAllProducts();
            request.setAttribute("products", productList); 
            request.getRequestDispatcher("/Products.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to retrieve products.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action != null) {
            switch (action) {
                case "create":
                    createProduct(request, response);
                    break;
                case "update":
                    updateProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action.");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action parameter is missing.");
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productId = request.getParameter("productId");
            String title = request.getParameter("title");
            String imageUrl = request.getParameter("imageUrl"); 
            String productUrl = request.getParameter("productUrl"); 
            
            String defaultImageUrl = "https://m.media-amazon.com/images/G/01/gc/designs/livepreview/amazon_dkblue_noto_email_v2016_us-main._CB468775337_.png";
            String defaultProductUrl = "https://www.amazon.com/"; 

            if (imageUrl == null || imageUrl.isEmpty()) {
                imageUrl = defaultImageUrl;
            }

            if (productUrl == null || productUrl.isEmpty()) {
                productUrl = defaultProductUrl;
            }

            Products newProduct = new Products(productId, title, imageUrl, productUrl, 0, 0, 0, 0, null, false, 0, 0, 0, 0, 0);
            productsDao.create(newProduct);

            response.sendRedirect(request.getContextPath() + "/products");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to create product.");
        }
    }




    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productId = request.getParameter("productId");
            String newTitle = request.getParameter("newTitle");

            Products product = productsDao.getProductById(productId);
            if (product != null) {
                product.setTitle(newTitle);
                productsDao.update(product);
                response.sendRedirect(request.getContextPath() + "/products");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update product.");
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String productId = request.getParameter("productId");
            Products product = productsDao.getProductById(productId);
            if (product != null) {
                productsDao.delete(product);
                response.sendRedirect(request.getContextPath() + "/products");
            } else {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Product not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete product.");
        }
    }
}
