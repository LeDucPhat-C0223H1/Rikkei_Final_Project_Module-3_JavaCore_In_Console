package bussiness.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {
    private long id;
    private LocalDateTime createdDate;
    private LocalDateTime estimatedDelivery; // ngày giao hàng dự kiến
    private String address;
    private long customerId;
    private OrderStatus orderStatus; // đang chờ xử lý || đang giao || hủy
    private boolean type; // phân loại làm đơn hàng hay là hóa đơn
    private double total; // tổng tiền

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Order() {
    }

    public Order(int id, LocalDateTime createdDate, LocalDateTime estimatedDelivery, String address, int customerId, OrderStatus orderStatus, boolean type, double total) {
        this.id = id;
        this.createdDate = createdDate;
        this.estimatedDelivery = estimatedDelivery;
        this.address = address;
        this.customerId = customerId;
        this.orderStatus = orderStatus;
        this.type = type;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(LocalDateTime estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", estimatedDelivery=" + estimatedDelivery +
                ", address='" + address + '\'' +
                ", customerId=" + customerId +
                ", orderStatus=" + orderStatus +
                ", type=" + type +
                ", total=" + total +
                '}';
    }
}
