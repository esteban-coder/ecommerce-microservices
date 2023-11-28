package pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Product;

@FeignClient(name = "ecommerce-products")
public interface ProductService {

    @GetMapping("/products/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable Long productId);
}
