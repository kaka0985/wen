package com.tledu.service;

import com.tledu.model.Order;
import com.tledu.model.User;

import java.util.List;

public interface IOrderService {
    void addOrder(Order order);

    List<Order> getOrderById(User user);
}
