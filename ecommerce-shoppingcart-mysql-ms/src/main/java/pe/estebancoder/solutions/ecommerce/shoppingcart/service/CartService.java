package pe.estebancoder.solutions.ecommerce.shoppingcart.service;

import pe.estebancoder.solutions.ecommerce.shoppingcart.feignclient.response.Product;
import pe.estebancoder.solutions.ecommerce.shoppingcart.model.Cart;

import java.util.List;

public interface CartService {

    Cart getCurrentCart(Long customerId);
    Cart addToCart(Long customerId, Long productId, int quantity);

    Cart updateCartItem(Long customerId, Long itemId, int newQuantity);

    Cart removeItemFromCart(Long customerId, Long itemId);

    Cart checkoutCart(Long customerId);

    Cart cancelCart(Long customerId);

    List<Product> getProductsFromCart(Long customerId);
}
