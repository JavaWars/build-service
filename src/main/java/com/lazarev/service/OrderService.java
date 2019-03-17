package com.lazarev.service;

import com.lazarev.model.Order;
import com.lazarev.model.User;
import com.lazarev.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getOrdersByCurrentUser() {
        List <Order> userOrders=orderRepository.findAllOrdersByUserId(UserService.getCurrentUser().getId());
        for (Order o:userOrders){
            o.getOrderedProduct();
        }
        return userOrders;
    }

    public List<Order> getOrdersByCurrentDeveloper(long developerId) {
        return orderRepository.findForDeveloper(developerId);
    }

    public void cancelOrder(Long orderId) {
        //return money todo
        Order o=orderRepository.getOne(orderId);
        o.setStatus("DEV_CLODE");
        orderRepository.save(o);
    }

    public void sentOrder(Long orderId) {
        Order o=orderRepository.getOne(orderId);
        o.setStatus("sent"+new Date().toString());
        orderRepository.save(o);
    }
}
