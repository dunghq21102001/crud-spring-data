package com.BEFresherTP.service;

import com.BEFresherTP.DTO.CategoryDTO;
import com.BEFresherTP.entity.Category;
import com.BEFresherTP.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface CategoryService {



    public List<CategoryDTO> findAll(boolean isDeleted);

    public CategoryDTO findById(int id);

    public CategoryDTO save(CategoryDTO categoryDto);

    public CategoryDTO update(CategoryDTO categoryDTO, int id);

    public void delete(int id);

    public void softDelete(int id);

}
