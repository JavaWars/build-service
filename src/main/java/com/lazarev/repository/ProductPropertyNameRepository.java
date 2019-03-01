package com.lazarev.repository;

import com.lazarev.model.ProductPropertyName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPropertyNameRepository extends JpaRepository<ProductPropertyName,Long> {

}
