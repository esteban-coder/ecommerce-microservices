package pe.estebancoder.solutions.ecommerce.products.service;

import pe.estebancoder.solutions.ecommerce.products.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProducts();
    Optional<Product> getProductById(Long productId);
    Product saveProduct(Product product);
    Product updateProduct(Long productId, Product updatedProduct);
    void deleteProduct(Long productId);

    List<Product> getProductsByIds(List<Long> productIds);
}
