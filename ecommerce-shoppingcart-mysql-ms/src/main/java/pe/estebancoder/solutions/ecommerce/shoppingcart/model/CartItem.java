package pe.estebancoder.solutions.ecommerce.shoppingcart.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    @JsonIgnoreProperties("cartItems")
    private Cart cart;

    //@Column(name = "product_id")
    private Long productId;

    private int quantity;
    private double price;

    public CartItem() {
    }

    public CartItem(Long itemId, Cart cart, Long productId, int quantity, double price) {
        this.itemId = itemId;
        this.cart = cart;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
