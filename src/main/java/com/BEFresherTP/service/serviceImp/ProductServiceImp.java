package com.BEFresherTP.service.serviceImp;

import com.BEFresherTP.DTO.ProductDTO;
import com.BEFresherTP.entity.Product;
import com.BEFresherTP.repository.ProductRepository;
import com.BEFresherTP.service.ProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> product = productRepository.findAllByDeletedFalse();
        return product.stream()
                .map(p -> this.modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).get();
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO addProduct(ProductDTO productDTO) {
        Product product = this.modelMapper.map(productDTO, Product.class);
        productRepository.save(product);
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, int id) {
        Product product = productRepository.findById(id).get();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setQuantity(productDTO.getQuantity());
        product.setImage(productDTO.getImage());
        product.setCategory(productDTO.getCategory());

        Product productSaved = productRepository.save(product);
        return this.modelMapper.map(productSaved, ProductDTO.class);
    }

    @Override
    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> searchListProduct(String name, Double minPrice, Double maxPrice, Integer categoryId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);

        Root<Product> root = cq.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(root.get("name"), name));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (categoryId != null) {
            predicates.add(cb.equal(root.get("category").get("id"), categoryId));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        List<Product> result = entityManager.createQuery(cq).getResultList();

        return result.stream()
                .map(p -> modelMapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDTO> findAllProducts(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(p -> this.modelMapper.map(p, ProductDTO.class));
    }

    @Override
    public Slice<ProductDTO> findAllProductsSlice(Pageable pageable) {
        Slice<Product> products = productRepository.findAllByDeletedFalse(pageable);
        return products.map(p -> this.modelMapper.map(p, ProductDTO.class));
    }

    @Override
    public Page<ProductDTO> findAllProductsByPriceAndName(Pageable pageable, List<String> names, List<Double> prices) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        List<Predicate> predicates = new ArrayList<>();
        if (names != null && !names.isEmpty()) {
            predicates.add(root.get("name").in(names));
        }

        if (prices != null && !prices.isEmpty()) {
            predicates.add(root.get("price").in(prices));
        }

        cq.where(predicates.toArray(new Predicate[0]));

        List<Product> products = entityManager.createQuery(cq).getResultList();
        Page<Product> productsPage = new PageImpl<>(products, pageable, products.size());
        return productsPage.map(p -> this.modelMapper.map(p, ProductDTO.class));
    }

    @Override
    public List<ProductDTO> sortProductsByPriceOrName(String sortBy) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> cq = cb.createQuery(Product.class);
        Root<Product> root = cq.from(Product.class);
        cq.select(root);
        switch (sortBy) {
            case "priceAsc":
                cq.orderBy(cb.asc(root.get("price")));
                break;
            case "priceDesc":
                cq.orderBy(cb.desc(root.get("price")));
                break;
            case "nameAsc":
                cq.orderBy(cb.asc(root.get("name")));
                break;
            case "nameDesc":
                cq.orderBy(cb.desc(root.get("name")));
                break;
        }

        List<Product> products = entityManager.createQuery(cq).getResultList();
        return products.stream().map(p -> this.modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
    }
}
