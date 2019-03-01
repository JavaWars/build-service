package com.lazarev.repository;

import com.lazarev.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

    //// TODO: 01.03.2019  fix function
//    @Query(nativeQuery = true,
//            value = "select * FROM order ")
//    List<Order> findAllOrdersByUserId(Long userId);
}
