package pe.estebancoder.solutions.ecommerce.products.service.impl;

import org.springframework.stereotype.Service;
import pe.estebancoder.solutions.ecommerce.products.model.Product;
import pe.estebancoder.solutions.ecommerce.products.repository.ProductRepository;
import pe.estebancoder.solutions.ecommerce.products.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtiene todos los productos.
     *
     * @return Lista de todos los productos.
     */
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Obtiene un producto por su ID.
     *
     * @param productId ID del producto a buscar.
     * @return El producto si se encuentra, o un Optional vacío.
     */
    @Override
    public Optional<Product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    /**
     * Guarda un producto.
     *
     * @param product Producto a guardar.
     * @return El producto guardado.
     */
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Actualiza un producto por su ID.
     *
     * @param productId ID del producto a actualizar.
     * @param updatedProduct Producto con los datos actualizados.
     * @return El producto actualizado si existe, o null si no se encuentra.
     */
    @Override
    public Product updateProduct(Long productId, Product updatedProduct) {
        if (productRepository.existsById(productId)) {
            updatedProduct.setId(productId); // Establece el ID para garantizar la actualización del producto correcto
            return productRepository.save(updatedProduct);
        } else {
            // Manejar la lógica si el producto no existe
            return null;
        }
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param productId ID del producto a eliminar.
     */
    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

