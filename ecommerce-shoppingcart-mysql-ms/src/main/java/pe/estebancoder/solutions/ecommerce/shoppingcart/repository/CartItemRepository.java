package pe.estebancoder.solutions.ecommerce.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.estebancoder.solutions.ecommerce.shoppingcart.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
