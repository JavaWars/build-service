package com.lazarev.service;

import com.lazarev.model.Order;
import com.lazarev.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByUserId(Long userId) {
        // TODO: 01.03.2019
        return null;//orderRepository.findAllOrdersByUserId(userId);
    }
}
