package bussiness.service;

import bussiness.entity.Customer;

import java.util.List;

public interface ICustomerService extends IGeneric<Customer, Long> {
    Long getNewId();

    List<Customer> findByName(String input);

    Customer login(String username, String pass);

    boolean existByUserName(String username);

    boolean existByEmail(String email);

    boolean existByPhone(String phone);
}
