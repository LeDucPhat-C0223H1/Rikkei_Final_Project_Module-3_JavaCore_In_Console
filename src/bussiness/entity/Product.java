package bussiness.entity;

import run.ShopManager;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Product implements Serializable {
    private long productId;
    private long categoryId;
    private String productName;
    private String description;
    private double unitPrice;        //đơn giá
    private int stock;               //số lượng trong kho
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean status;

    public Product() {
    }

    public Product(long productId, long categoryId, String productName, String description, double unitPrice, int stock) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.productName = productName;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stock = stock;
        this.createdAt = LocalDate.now();
        this.status = true;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "\u001B[35mid=" + productId +
                " __ Danh mục: " + ShopManager.catalogService.findById(categoryId).getCatalogName() +
                " , Tên sản phẩm: " + productName +
                " , Ngày tạo: " + createdAt + "\n" +

                "Giá bán: " + unitPrice +
                " , Số lượng: " + stock +
                " , Mô tả: " + description +
                " , Trạng thái: " + (status ? "Hiển thị" : "Bị ẩn")  + "\u001B[97m";
    }
}
