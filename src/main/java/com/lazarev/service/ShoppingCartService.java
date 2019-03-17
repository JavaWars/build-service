package com.lazarev.service;

import com.lazarev.model.Order;
import com.lazarev.model.Product;
import com.lazarev.model.TransactionOrder;
import com.lazarev.model.User;
import com.lazarev.repository.OrderRepository;
import com.lazarev.repository.ProductRepository;
import com.lazarev.repository.TransactionOrderRepository;
import com.lazarev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {

    @Autowired
    private ProductRepository productRepository;

    private List<Order> cart=new LinkedList<>();

    public void addToCart(long productId, Integer count) {

        System.out.println(productId+"|"+count);

        if (count==null|| count==0){count=1;}

        deleteFromCart(productId);

        Order tempOrder = new Order();

        Product p = productRepository.getOne(productId);

        tempOrder.setCount(count);
        tempOrder.setOrderedProduct(p);
        cart.add(tempOrder);
    }

    public void deleteFromCart(long productId) {

        for (Order o : cart) {
            if (o.getOrderedProduct().getId() == productId) {
                cart.remove(o);
            }
        }
    }

    public List<Order> getAllOrders() {
        checking();
        return cart;
    }

    //updating info if changed
    private void checking() {

        System.out.println(cart);

        for (Order o : cart) {
            if (o.getOrderedProduct().getPrice()!= productRepository.getOne(o.getOrderedProduct().getId()).getPrice()){
                System.out.println("product will be updated");
                deleteFromCart(o.getOrderedProduct().getId());
                addToCart(o.getOrderedProduct().getId(),o.getCount());
            }
        }
    }

    public Double getTotalPrice() {
        Double result=new Double(0);
        for (Order o : cart) {
            result+=o.getOrderedProduct().getPrice()*o.getCount();
        }
        return result;
    }

    @Autowired
    private TransactionOrderRepository transactionOrderRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;
    public void makeOrderNow() {
        System.out.println("making order");
//        checking();//todo inform user about updating price
        User u=UserService.getCurrentUser();
        TransactionOrder to=new TransactionOrder();

        for (Order o : cart) {
            o.setUser(userRepository.getOne(u.getId()));
            o.setOrderedProduct(productRepository.getOne(o.getOrderedProduct().getId()));
            o.setStatus("");
            orderRepository.save(o);
        }

        to.setOrderCreationDate(new Date());
        to.setOrders(cart);
        transactionOrderRepository.save(to);

        cart.clear();
    }
}
