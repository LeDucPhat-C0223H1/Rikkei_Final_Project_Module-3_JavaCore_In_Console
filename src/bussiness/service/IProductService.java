package bussiness.service;

import bussiness.entity.Catalog;
import bussiness.entity.Product;

import java.util.List;

public interface IProductService extends IGeneric<Product, Long> {
    Long getNewId();

    List<Product> findByName(String input);

    Product findProductIsActiveById(Long id);

    List<Product> findProductStatusIsActive();

    List<Product> findProductByNameOrDescForCustomer(String input);

    List<Product> findTop10NewProductByDate();
}
