package pe.estebancoder.solutions.ecommerce.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.estebancoder.solutions.ecommerce.products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
