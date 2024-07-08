package com.BEFresherTP.controller;

import com.BEFresherTP.DTO.CategoryDTO;
import com.BEFresherTP.service.CategoryService;
import com.BEFresherTP.service.serviceImp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryServiceImp categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> findAll() {
        List<CategoryDTO> categories =  categoryService.findAll(false);
        return ResponseEntity.status(HttpStatus.OK).body(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> findById(@PathVariable int id) {
        CategoryDTO categoryDTO = categoryService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> saveCategory(@RequestBody CategoryDTO category) {
        CategoryDTO categoryDTO = categoryService.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        CategoryDTO categoryDtoUpdated = categoryService.update(categoryDTO, id);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtoUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
            categoryService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Category");

    }

    @DeleteMapping("/soft-delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable int id) {
        categoryService.softDelete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Category");
    }
}
