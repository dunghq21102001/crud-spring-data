package com.BEFresherTP.service.serviceImp;

import com.BEFresherTP.DTO.CategoryDTO;
import com.BEFresherTP.entity.Category;
import com.BEFresherTP.repository.CategoryRepository;
import com.BEFresherTP.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<CategoryDTO> findAll(boolean isDeleted) {
        List<Category> categories = categoryRepository.findAllByDeleted(isDeleted);
        return categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO findById(int id) {
        Category category = categoryRepository.findById(id).get();
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category categorySaved = categoryRepository.save(category);

        return this.modelMapper.map(categorySaved, CategoryDTO.class);
    }

    @Override
    public CategoryDTO update(CategoryDTO categoryDTO, int id) {
        Category existCate = categoryRepository.findById(id).get();

        existCate.setName(categoryDTO.getName());
        Category categoryUpdated = categoryRepository.save(existCate);
        return this.modelMapper.map(categoryUpdated, CategoryDTO.class);
    }

    @Override
    public void delete(int id) {
        Category existCate = categoryRepository.findById(id).get();
        categoryRepository.deleteById(existCate.getId());
    }

    @Override
    public void softDelete(int id) {
        Category existCate = categoryRepository.findById(id).get();
        existCate.setDeleted(true);
        categoryRepository.save(existCate);
    }
}
