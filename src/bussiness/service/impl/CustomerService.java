package bussiness.service.impl;

import bussiness.config.IOFile;
import bussiness.entity.Customer;
import bussiness.service.ICustomerService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerService implements ICustomerService {
    private List<Customer> customers;

    public CustomerService() {
        this.customers = IOFile.readFromFile(IOFile.CUSTOMER_PATH);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(Long id) {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Customer> findByName(final String input) {
        return customers.stream()
                .filter(c -> c.getUsername().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Customer customer) {
        if (findById(customer.getId()) != null) {
            // đã tồn tại -> chỉnh sửa
            customers.set(customers.indexOf(findById(customer.getId())), customer);
        } else {
            // thêm mới
            customers.add(customer);
        }
        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customers);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        customers.remove(findById(id));
        IOFile.writeToFile(IOFile.CUSTOMER_PATH, customers);
    }

    @Override
    public Long getNewId() {
        long idMax = customers.stream()
                .map(c -> c.getId())
                .max(Comparator.comparingLong(m -> m))
                .orElse(0L);
        return idMax + 1;
    }

    @Override
    public Customer login(String username, String pass) {
        return customers.stream()
                .filter(c -> c.getUsername().equals(username) && c.getPassword().equals(pass))
                .findFirst().orElse(null);
    }

    @Override
    public boolean existByUserName(String username) {
        return customers.stream().anyMatch(c -> c.getUsername().equals(username));
    }

    @Override
    public boolean existByEmail(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }

    @Override
    public boolean existByPhone(String phone) {
        return customers.stream().anyMatch(c -> c.getPhone().equals(phone));
    }
}
