package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);

        assertEquals(product.getProductId(), createdProduct.getProductId());
        assertEquals(product.getProductName(), createdProduct.getProductName());
        assertEquals(product.getProductQuantity(), createdProduct.getProductQuantity());
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllIfEmpty() {
        List<Product> productList = new ArrayList<>();
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> allProducts = productService.findAll();

        assertTrue(allProducts.isEmpty());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        Iterator<Product> productIterator = productList.iterator();

        when(productRepository.findAll()).thenReturn(productIterator);
        List<Product> allProducts = productService.findAll();

        assertEquals(2, allProducts.size());
        assertEquals(product1.getProductId(), allProducts.get(0).getProductId());
        assertEquals(product2.getProductId(), allProducts.get(1).getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        Product modifiedProduct = new Product();
        modifiedProduct.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        modifiedProduct.setProductName("Sampo Cap Usep");
        modifiedProduct.setProductQuantity(50);

        when(productRepository.edit(modifiedProduct)).thenReturn(modifiedProduct);
        Product result = productService.edit(modifiedProduct);

        assertEquals(modifiedProduct.getProductId(), result.getProductId());
        assertEquals(modifiedProduct.getProductName(), result.getProductName());
        assertEquals(modifiedProduct.getProductQuantity(), result.getProductQuantity());
        verify(productRepository, times(1)).edit(modifiedProduct);
    }

    @Test
    void testDeleteProduct() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        doNothing().when(productRepository).delete(productId);
        productService.delete(productId);

        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);

        when(productRepository.findById(product.getProductId())).thenReturn(product);
        Product result = productService.findById(product.getProductId());

        assertEquals(product.getProductId(), result.getProductId());
        assertEquals(product.getProductName(), result.getProductName());
        assertEquals(product.getProductQuantity(), result.getProductQuantity());
        verify(productRepository, times(1)).findById(product.getProductId());
    }

    @Test
    void testFindByIdIfProductNotFound() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";

        when(productRepository.findById(productId)).thenReturn(null);
        Product result = productService.findById(productId);

        assertNull(result);
        verify(productRepository, times(1)).findById(productId);
    }
}

