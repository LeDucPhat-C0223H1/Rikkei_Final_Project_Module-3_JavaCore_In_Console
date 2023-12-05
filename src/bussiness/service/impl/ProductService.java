package bussiness.service.impl;

import bussiness.config.IOFile;
import bussiness.entity.Product;
import bussiness.service.IProductService;
import run.ShopManager;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements IProductService {
    private List<Product> products;

    public ProductService() {
        this.products = IOFile.readFromFile(IOFile.PRODUCTS_PATH);
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Long getNewId() {
        long idMax = products.stream()
                .map(p -> p.getProductId())
                .max(Comparator.comparingLong(m -> m))
                .orElse(0L);
        return idMax + 1;
    }

    @Override
    public Product findById(Long id) {
        return products.stream()
                .filter(p -> p.getProductId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Product> findByName(String input) {
        return products.stream()
                .filter(p -> p.getProductName().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findProductStatusIsActive() {
        // Xét trạng thái danh mục và trạng thái sản phẩm
        return products.stream()
                .filter(p -> {
                    boolean catalogStatus = ShopManager.catalogService.findById(p.getCategoryId()).isStatus();
                    return catalogStatus && p.isStatus();
                })
                .collect(Collectors.toList());
    }

    @Override
    public Product findProductIsActiveById(Long id) {
        return findProductStatusIsActive().stream()
                .filter(p -> p.getProductId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public List<Product> findProductByNameOrDescForCustomer(String input) {
        return findProductStatusIsActive().stream()
                .filter(p -> p.getProductName().toLowerCase().contains(input.toLowerCase()) || p.getDescription().toLowerCase().contains(input.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findTop10NewProductByDate() {
        Comparator<Product> comparator = (o1, o2) -> {
            if (o1.getCreatedAt().isEqual(o2.getCreatedAt())) {
                return Double.compare(o2.getUnitPrice(), o1.getUnitPrice());
            }
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        };

        return findProductStatusIsActive().stream()
                .sorted(comparator.reversed())
                .limit(10)
                .collect(Collectors.toList());
    }

    @Override
    public boolean save(Product product) {
        if (findById(product.getProductId()) != null) {
            // đã tồn tại -> chỉnh sửa
            products.set(products.indexOf(findById(product.getProductId())), product);
        } else {
            // thêm mới
            products.add(product);
        }
        IOFile.writeToFile(IOFile.PRODUCTS_PATH, products);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        products.remove(findById(id));
        IOFile.writeToFile(IOFile.PRODUCTS_PATH, products);
    }
}
