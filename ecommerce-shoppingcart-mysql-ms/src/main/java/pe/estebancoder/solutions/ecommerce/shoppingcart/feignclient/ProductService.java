package pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Product;

import java.util.List;

@FeignClient(name = "ecommerce-products")
public interface ProductService {

    @GetMapping("/products/{productId}")
    ResponseEntity<Product> getProductById(@PathVariable Long productId);

    @GetMapping("/products/getByIds")
    ResponseEntity<List<Product>> getProductsByIds(@RequestParam List<Long> productIds);
}
