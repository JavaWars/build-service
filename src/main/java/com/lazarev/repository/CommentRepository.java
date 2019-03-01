package com.lazarev.repository;

import com.lazarev.model.Comment;
import com.lazarev.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query(nativeQuery = true,
            value = "select * from Comments where Comments.product_id=:prod_id")
    List<Comment> getCommentByProduct(Long prod_id);
}
