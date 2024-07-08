package com.BEFresherTP.repository;

import com.BEFresherTP.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAllByDeletedFalse();

    Page<Product> findAll(Pageable pageable);

    Slice<Product> findAllByDeletedFalse(Pageable pageable);
}
