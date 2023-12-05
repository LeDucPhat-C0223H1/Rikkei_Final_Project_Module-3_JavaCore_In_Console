package bussiness.service.impl;

import bussiness.config.IOFile;
import bussiness.entity.CartItem;
import bussiness.entity.Customer;
import bussiness.entity.OrderDetail;
import bussiness.service.ICartService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import run.ShopManager;

import java.util.List;
import java.util.stream.Collectors;

public class CartService implements ICartService {
    private List<CartItem> cart;

    public CartService(Customer userLogin) {
        this.cart = userLogin.getCart();
    }

    @Override
    public List<CartItem> findAll() {
        return cart;
    }

    @Override
    public CartItem findById(Long id) {
        return cart.stream()
                .filter(c -> c.getProduct().getProductId() == id)
                .findFirst().orElse(null);
    }

    @Override
    public boolean save(CartItem cartItem) {
        CartItem oldCartItem = findById(cartItem.getProduct().getProductId());
        if (oldCartItem != null) {
            // đã tồn tại
            cartItem.setQuantity(cartItem.getQuantity() + oldCartItem.getQuantity());
        } else  {
            // chưa tồn tại, thêm mới
            cart.add(cartItem);
        }
        ShopManager.customerService.save(ShopManager.currentLogin);
        return true;
    }

    @Override
    public void deleteById(Long id) {
        cart.remove(findById(id));
        IOFile.writeToFile(IOFile.CUSTOMER_PATH,ShopManager.customerService.findAll() );
    }

    @Override
    public boolean saveEditQuantity(CartItem cartItem) {
        ShopManager.customerService.save(ShopManager.currentLogin);
        return true;
    }
}
