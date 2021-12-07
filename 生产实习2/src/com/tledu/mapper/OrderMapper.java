package com.tledu.mapper;

import com.tledu.model.Book;
import com.tledu.model.Order;
import com.tledu.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface OrderMapper {

    void addOrder(Order order);

    List<Order> getOrderById(User user);
}
