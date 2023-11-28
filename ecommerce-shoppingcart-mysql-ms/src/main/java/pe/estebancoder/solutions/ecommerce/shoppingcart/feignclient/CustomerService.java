package pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Customer;

@FeignClient(name = "ecommerce-customers")
public interface CustomerService {

    @GetMapping("/customers/{customerId}")
    ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId);
}
