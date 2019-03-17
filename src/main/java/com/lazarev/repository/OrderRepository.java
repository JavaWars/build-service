package com.lazarev.repository;

import com.lazarev.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(nativeQuery = true,
            value = "SELECT order_product.*\n" +
                    "FROM order_product \n" +
                    "WHERE order_product.user_id = :userId \n")
    List<Order> findAllOrdersByUserId(Long userId);

    @Query(nativeQuery = true,
    value = "SELECT order_product.*  FROM order_product , product\n" +
            "WHERE order_product.product_id = product.product_id and product.developer_id=:developerId\n")
    List<Order> findForDeveloper(long developerId);
}
