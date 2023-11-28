package pe.estebancoder.solutions.ecommerce.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.estebancoder.solutions.ecommerce.shoppingcart.model.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCustomerIdAndStatus(Long customerId, Cart.CartStatus status);
}
