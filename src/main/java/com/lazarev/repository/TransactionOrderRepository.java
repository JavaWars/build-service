package com.lazarev.repository;

import com.lazarev.model.Product;
import com.lazarev.model.TransactionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionOrderRepository extends JpaRepository<TransactionOrder,Long> {
}
