package com.lazarev.service;

import com.lazarev.model.Comment;
import com.lazarev.model.Product;
import com.lazarev.repository.CommentRepository;
import com.lazarev.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ProductRepository productRepository;
    public List<Comment> getCommentByProductId(Long productId) {
        return commentRepository.getCommentByProduct(productId);
    }

    public void inset(Comment comment) {

    }
}
