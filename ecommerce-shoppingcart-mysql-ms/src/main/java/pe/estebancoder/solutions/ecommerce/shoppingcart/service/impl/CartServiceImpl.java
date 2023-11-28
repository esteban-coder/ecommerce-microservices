package pe.estebancoder.solutions.ecommerce.shoppingcart.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.CustomerService;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.ProductService;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Customer;
import pe.estebancoder.solutions.ecommerce.shoppingcart.model.Cart;
import pe.estebancoder.solutions.ecommerce.shoppingcart.model.CartItem;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Product;
import pe.estebancoder.solutions.ecommerce.shoppingcart.repository.CartItemRepository;
import pe.estebancoder.solutions.ecommerce.shoppingcart.repository.CartRepository;
import pe.estebancoder.solutions.ecommerce.shoppingcart.service.CartService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    private final CustomerService customerService;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductService productService, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
        this.customerService = customerService;
    }

    private Cart createCart(Long customerId) {
        Cart newCart = new Cart();
        newCart.setCustomerId(customerId);
        newCart.setCreatedAt(LocalDateTime.now());
        newCart.setStatus(Cart.CartStatus.IN_PROCESS);
        newCart.setCartItems(new ArrayList<>());
        return cartRepository.save(newCart);
    }

    public Cart getCurrentCart(Long customerId) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndStatus(customerId, Cart.CartStatus.IN_PROCESS);
        return optionalCart.orElse(null);
    }

    @Override
    public Cart addToCart(Long customerId, Long productId, int quantity) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndStatus(customerId, Cart.CartStatus.IN_PROCESS);
        Cart cart = optionalCart.orElseGet(() -> createCart(customerId));

        Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .findFirst();

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity); // Incrementar la cantidad si el producto ya está en el carrito
            cartItemRepository.save(cartItem); // Guardar el CartItem actualizado
        } else {
            // llamar al microservicio producto para obtener su precio
            ResponseEntity<Product> responseEntity = productService.getProductById(productId);
            Product product = responseEntity.getBody();

            if(product != null) {
                CartItem cartItem = new CartItem();
                cartItem.setProductId(productId);
                cartItem.setQuantity(quantity);
                cartItem.setCart(cart);
                cartItem.setPrice(product.getPrice()); // Grabar el precio del producto en el momento de agregarlo al carrito
                cart.getCartItems().add(cartItem);
                cartItemRepository.save(cartItem); // Guardar el nuevo CartItem
            } else {
                throw new IllegalArgumentException("Producto no encontrado");
            }
        }

        return cart;
        // return cartRepository.findById(cart.getCartId()).orElseThrow(() -> new RuntimeException("Carrito no encontrado"));
    }

    @Override
    public Cart updateCartItem(Long customerId, Long itemId, int newQuantity) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndStatus(customerId, Cart.CartStatus.IN_PROCESS);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getItemId().equals(itemId))
                    .findFirst();

            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cartItem.setQuantity(newQuantity);
                cartItemRepository.save(cartItem);

                return cart;
                //return cartRepository.findById(cart.getCartId()).orElseThrow(() -> new RuntimeException("Error al obtener el carrito actualizado"));
            } else {
                throw new IllegalArgumentException("Ítem no encontrado en el carrito");
            }
        } else {
            throw new IllegalArgumentException("Carrito no encontrado o no está en proceso");
        }
    }

    @Override
    public Cart removeItemFromCart(Long customerId, Long itemId) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndStatus(customerId, Cart.CartStatus.IN_PROCESS);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            Optional<CartItem> optionalCartItem = cart.getCartItems().stream()
                    .filter(item -> item.getItemId().equals(itemId))
                    .findFirst();

            if (optionalCartItem.isPresent()) {
                CartItem cartItem = optionalCartItem.get();
                cart.getCartItems().remove(cartItem);
                cartItemRepository.delete(cartItem);

                return cart;
                //return cartItem.getCart();
                // Obtener el carrito actualizado desde la base de datos
                //return cartRepository.findById(cart.getCartId()).orElseThrow(() -> new RuntimeException("Error al obtener el carrito actualizado"));
            } else {
                throw new IllegalArgumentException("Ítem no encontrado en el carrito");
            }
        } else {
            throw new IllegalArgumentException("Carrito no encontrado o no está en proceso");
        }
    }

    @Override
    public Cart checkoutCart(Long customerId) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndStatus(customerId, Cart.CartStatus.IN_PROCESS);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setStatus(Cart.CartStatus.COMPLETED);
            cart.setConfirmedAt(LocalDateTime.now()); // Establecer la hora de confirmación al realizar el checkout
            // Agregar la lógica para procesar el pago, generar una orden, etc.

            cart = cartRepository.save(cart);

            // Enviar correo de confirmación al cliente
            ResponseEntity<Customer> responseEntity = customerService.getCustomerById(customerId);
            Customer customer = responseEntity.getBody();
            if(customer!= null){
                System.out.println("customer.email: " + customer.getEmail());
                // Enviar por correo una notificación de la compra realizada
            } else {
                throw new IllegalArgumentException("Cliente no encontrado");
            }

            return cart;
        } else {
            throw new IllegalArgumentException("Carrito no encontrado o no está en proceso");
        }
    }

    @Override
    public Cart cancelCart(Long customerId) {
        Optional<Cart> optionalCart = cartRepository.findByCustomerIdAndStatus(customerId, Cart.CartStatus.IN_PROCESS);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            cart.setStatus(Cart.CartStatus.CANCELLED); // Establecer el estado como "cancelado"
            return cartRepository.save(cart);
        } else {
            throw new IllegalArgumentException("No se encontró ningún carrito en proceso para cancelar");
        }
    }
}
