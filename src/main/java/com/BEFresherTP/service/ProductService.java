package com.BEFresherTP.service;

import com.BEFresherTP.DTO.ProductDTO;
import com.BEFresherTP.entity.Product;
import com.BEFresherTP.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public interface ProductService {



    public List<ProductDTO> getAllProducts();

    public ProductDTO getProductById(int id);

    public ProductDTO addProduct(ProductDTO productDTO);

    public ProductDTO updateProduct(ProductDTO productDTO, int id);

    public void deleteProduct(int id);

    public List<ProductDTO> searchListProduct(String name, Double minPrice, Double maxPrice, Integer categoryId);

    public Page<ProductDTO> findAllProducts(Pageable pageable);

    public Slice<ProductDTO> findAllProductsSlice(Pageable pageable);

    public Page<ProductDTO> findAllProductsByPriceAndName(Pageable pageable,
                                                          List<String> names, List<Double> prices);

    public List<ProductDTO> sortProductsByPriceOrName(String sortBy);
}
