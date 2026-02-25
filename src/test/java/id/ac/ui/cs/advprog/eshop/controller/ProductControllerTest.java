package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    ProductService productService;

    @Mock
    Model model;

    @InjectMocks
    ProductController productController;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);

        assertEquals("CreateProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.create(product)).thenReturn(product);
        String viewName = productController.createProductPost(product, model);

        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testEditProductPage() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productService.findById(product.getProductId())).thenReturn(product);
        String viewName = productController.editProductPage(product.getProductId(), model);

        assertEquals("EditProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
        verify(productService, times(1)).findById(product.getProductId());
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Usep");
        product.setProductQuantity(50);

        when(productService.edit(product)).thenReturn(product);
        String viewName = productController.editProductPost(product, model);

        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).edit(product);
    }

    @Test
    void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        doNothing().when(productService).delete(productId);
        String viewName = productController.deleteProduct(productId);

        assertEquals("redirect:list", viewName);
        verify(productService, times(1)).delete(productId);
    }

    @Test
    void testProductListPage() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productList.add(product);

        when(productService.findAll()).thenReturn(productList);
        String viewName = productController.productListPage(model);

        assertEquals("ListProduct", viewName);
        verify(model, times(1)).addAttribute("products", productList);
        verify(productService, times(1)).findAll();
    }

    @Test
    void testProductListPageEmpty() {
        List<Product> productList = new ArrayList<>();

        when(productService.findAll()).thenReturn(productList);
        String viewName = productController.productListPage(model);

        assertEquals("ListProduct", viewName);
        verify(model, times(1)).addAttribute("products", productList);
        verify(productService, times(1)).findAll();
    }
}

