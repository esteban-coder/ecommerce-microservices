package pe.estebancoder.solutions.ecommerce.customers.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.estebancoder.solutions.ecommerce.customers.model.Customer;
import pe.estebancoder.solutions.ecommerce.customers.repository.CustomerRepository;
import pe.estebancoder.solutions.ecommerce.customers.service.CustomerService;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Obtiene todos los clientes.
     *
     * @return Lista de todos los clientes.
     */
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    /**
     * Obtiene un cliente por su ID.
     *
     * @param customerId ID del cliente a buscar.
     * @return El cliente si se encuentra, o un Optional vacío.
     */
    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    /**
     * Guarda un cliente.
     *
     * @param customer Cliente a guardar.
     * @return El cliente guardado.
     */
    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    /**
     * Actualiza un cliente por su ID.
     *
     * @param customerId    ID del cliente a actualizar.
     * @param updatedCustomer Cliente con los datos actualizados.
     * @return El cliente actualizado si existe, o null si no se encuentra.
     */
    @Override
    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        if (customerRepository.existsById(customerId)) {
            updatedCustomer.setId(customerId); // Establece el ID para garantizar la actualización del cliente correcto
            return customerRepository.save(updatedCustomer);
        } else {
            // Manejar la lógica si el cliente no existe
            return null;
        }
    }

    /**
     * Elimina un cliente por su ID.
     *
     * @param customerId ID del cliente a eliminar.
     */
    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
