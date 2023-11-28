package pe.estebancoder.solutions.ecommerce.shoppingcart.dto;

public class UpdateCartItemRequest {
    private Long customerId;
    private Long itemId;
    private int newQuantity;

    public UpdateCartItemRequest() {
    }

    public UpdateCartItemRequest(Long customerId, Long itemId, int newQuantity) {
        this.customerId = customerId;
        this.itemId = itemId;
        this.newQuantity = newQuantity;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public int getNewQuantity() {
        return newQuantity;
    }

    public void setNewQuantity(int newQuantity) {
        this.newQuantity = newQuantity;
    }
}
