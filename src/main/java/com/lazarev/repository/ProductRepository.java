package com.lazarev.repository;

import com.lazarev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query(nativeQuery = true,
    value = "SELECT product.*\n" +
            "FROM developers , developer_admin , product\n" +
            "WHERE  ( (developers.user_id=:userId)or (developer_admin.user_id=:userId) ) and product_id=:productId\n" +
            "GROUP BY product_id")
    Product findByProductIdAndCurrentUser(Long productId, Long userId);

    @Query(nativeQuery = true,
    value = "select product.* from product where product.developer_id=:developerId")
    List<Product> findAllByDeveloper(Long developerId);
}
