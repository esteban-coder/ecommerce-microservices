package pe.estebancoder.solutions.ecommerce.shoppingcart.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Cart {

    public enum CartStatus {
        IN_PROCESS,
        CANCELLED,
        COMPLETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    //@Column(name = "customer_id")
    private Long customerId;

    //@Column(name = "created_at")
    private LocalDateTime createdAt;

    private LocalDateTime confirmedAt;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    //@Column(name = "total_amount")
    private double totalAmount;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("cart")
    private List<CartItem> cartItems;

    public Cart() {
    }

    public Cart(Long cartId, Long customerId, LocalDateTime createdAt, LocalDateTime confirmedAt, CartStatus status, double totalAmount, List<CartItem> cartItems) {
        this.cartId = cartId;
        this.customerId = customerId;
        this.createdAt = createdAt;
        this.confirmedAt = confirmedAt;
        this.status = status;
        this.totalAmount = totalAmount;
        this.cartItems = cartItems;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getConfirmedAt() {
        return confirmedAt;
    }

    public void setConfirmedAt(LocalDateTime confirmedAt) {
        this.confirmedAt = confirmedAt;
    }

    public CartStatus getStatus() {
        return status;
    }

    public void setStatus(CartStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
