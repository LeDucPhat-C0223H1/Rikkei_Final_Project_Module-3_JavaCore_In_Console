package bussiness.service;

import bussiness.entity.CartItem;
import bussiness.entity.Order;
import bussiness.entity.OrderDetail;

import java.util.List;

public interface ICartService extends IGeneric<CartItem, Long> {
    boolean saveEditQuantity (CartItem cartItem);
}
