package com.BEFresherTP.controller;

import com.BEFresherTP.DTO.ProductDTO;
import com.BEFresherTP.service.ProductService;
import com.BEFresherTP.service.serviceImp.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    ProductServiceImp productService;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> products() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable int id) {
        ProductDTO product = productService.getProductById(id);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product) {
        ProductDTO savedProduct = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable int id, @RequestBody ProductDTO product) {
        ProductDTO productUpdated = productService.updateProduct(product, id);
        return ResponseEntity.status(HttpStatus.OK).body(productUpdated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted Category");
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDTO>> searchProducts(@RequestParam(value = "name", required = false) String name,
                                                           @RequestParam(value = "minPrice", required = false) Double minPrice,
                                                           @RequestParam(value = "maxPrice", required = false) Double maxPrice,
                                                           @RequestParam(value = "categoryId", required = false) Integer categoryId) {

        List<ProductDTO> products = productService.searchListProduct(name, minPrice, maxPrice, categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(products);

    }

    @GetMapping("/paging-page")
    public ResponseEntity<Page<ProductDTO>> getProductsWithPage(@RequestParam(defaultValue = "0") Integer page,
                                                                @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.findAllProducts(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/paging-slice")
    public ResponseEntity<Slice<ProductDTO>> getProductsWithSlice(@RequestParam(defaultValue = "0") Integer page,
                                                                  @RequestParam(defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Slice<ProductDTO> products = productService.findAllProductsSlice(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(products);

    }

    @GetMapping("/search-multi")
    public ResponseEntity<Page<ProductDTO>> sortProducts(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) List<String> names,
            @RequestParam(required = false) List<Double> prices) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductDTO> products = productService.findAllProductsByPriceAndName(pageable, names, prices);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<ProductDTO>> sortProducts(@RequestParam(defaultValue = "priceAsc") String sortBy) {
        List<ProductDTO> productDTOS = productService.sortProductsByPriceOrName(sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(productDTOS);
    }
}
