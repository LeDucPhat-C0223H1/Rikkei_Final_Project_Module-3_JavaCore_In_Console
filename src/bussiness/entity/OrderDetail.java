package bussiness.entity;

import java.io.Serializable;

// Hóa đơn chi tiết
public class OrderDetail implements Serializable {
    private long id;
    private long orderId;
    private long foodId;
    private int quantity;
    private double price; // giá tại thời điểm mua

    public OrderDetail() {
    }

    public OrderDetail(long id, long orderId, long foodId, int quantity, double price) {
        this.id = id;
        this.orderId = orderId;
        this.foodId = foodId;
        this.quantity = quantity;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getFoodId() {
        return foodId;
    }

    public void setFoodId(long foodId) {
        this.foodId = foodId;
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
