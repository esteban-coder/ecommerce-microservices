package pe.estebancoder.solutions.ecommerce.shoppingcart.dto;

public class AddCartItemRequest {
    private Long customerId;
    private Long productId;
    private int quantity;

    public AddCartItemRequest() {
    }

    public AddCartItemRequest(Long customerId, Long productId, int quantity) {
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
