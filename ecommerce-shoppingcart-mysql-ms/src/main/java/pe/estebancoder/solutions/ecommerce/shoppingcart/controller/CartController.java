package pe.estebancoder.solutions.ecommerce.shoppingcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.estebancoder.solutions.ecommerce.shoppingcart.dto.AddCartItemRequest;
import pe.estebancoder.solutions.ecommerce.shoppingcart.dto.UpdateCartItemRequest;
import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Product;
import pe.estebancoder.solutions.ecommerce.shoppingcart.model.Cart;
import pe.estebancoder.solutions.ecommerce.shoppingcart.service.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/current/{customer_id}")
    public ResponseEntity<Cart> getCurrentCart(@PathVariable("customer_id") Long customerId) {
        Cart cart = cartService.getCurrentCart(customerId);
        if (cart != null) {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-item")
    //public ResponseEntity<Cart> addItemToCart(@RequestParam Long customerId, @RequestParam Long productId, @RequestParam int quantity) {
    public ResponseEntity<Cart> addItemToCart(@RequestBody AddCartItemRequest request) {
        Long customerId = request.getCustomerId();
        Long productId = request.getProductId();
        int quantity = request.getQuantity();

        Cart cart = cartService.addToCart(customerId, productId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/update-item")
    //public ResponseEntity<Cart> updateCartItem(@RequestParam Long customerId, @RequestParam Long itemId, @RequestParam int newQuantity) {
    public ResponseEntity<Cart> updateCartItem(@RequestBody UpdateCartItemRequest request) {
        Long customerId = request.getCustomerId();
        Long itemId = request.getItemId();
        int newQuantity = request.getNewQuantity();

        Cart cart = cartService.updateCartItem(customerId, itemId, newQuantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/remove-item")
    public ResponseEntity<Cart> removeItemFromCart(@RequestParam Long customerId, @RequestParam Long itemId) {
        Cart cart = cartService.removeItemFromCart(customerId, itemId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/checkout")
    public ResponseEntity<Cart> checkoutCart(@RequestParam Long customerId) {
        Cart cart = cartService.checkoutCart(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Cart> cancelCart(@RequestParam Long customerId) {
        Cart cart = cartService.cancelCart(customerId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @GetMapping("/products/{customer_id}")
    public ResponseEntity<List<Product>> getProductsFromCart(@PathVariable("customer_id") Long customerId) {
        List<Product> products = cartService.getProductsFromCart(customerId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}