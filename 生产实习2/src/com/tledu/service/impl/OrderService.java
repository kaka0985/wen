package com.tledu.service.impl;

import com.tledu.mapper.OrderMapper;
import com.tledu.model.Order;
import com.tledu.model.User;
import com.tledu.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Override
    public void addOrder(Order order) {
        orderMapper.addOrder(order);
    }

    @Override
    public List<Order> getOrderById(User user) {
        return orderMapper.getOrderById(user);
    }
}
