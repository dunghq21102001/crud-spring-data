package com.BEFresherTP.repository;

import com.BEFresherTP.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    public List<Category> findAllByDeleted(boolean False);
}
