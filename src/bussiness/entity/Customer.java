package bussiness.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Customer implements Serializable {
    private long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String phone;
    private boolean role;
    private boolean status;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private List<CartItem> cart;

    public Customer() {
    }


    public Customer(long id, String username, String password, String email, String fullName, String phone) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.phone = phone;
        this.role = false; // true: Quản trị viên -- false: Khách hàng
        this.status = true; // true: Hoạt động -- false: Bị khóa
        this.createdAt = LocalDate.now();
        this.cart = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isRole() {
        return role;
    }

    public void setRole(boolean role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getCreateDate() {
        return createdAt;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createdAt = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<CartItem> getCart() {
        return cart;
    }

    public void setCart(List<CartItem> cart) {
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "* " +
                "id=" + id +
                " __ Họ và Tên: " + fullName +
                ", SĐT: " + phone +
                ", Trạng thái: " + (status ? "Hoạt động" : "Bị khóa") + "\u001B[97m" ;
    }
}
