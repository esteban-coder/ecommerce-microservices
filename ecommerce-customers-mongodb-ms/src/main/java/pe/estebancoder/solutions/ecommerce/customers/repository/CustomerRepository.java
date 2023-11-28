package pe.estebancoder.solutions.ecommerce.customers.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pe.estebancoder.solutions.ecommerce.customers.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Long> {
}
