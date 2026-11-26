package com.shop.Product.Service;

import com.shop.Product.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ProductService {

    public ResponseEntity<Page<Product>> getProduct(Integer pageNo, Integer pageSize, Product product);
    public ResponseEntity<String> getProductById(Integer id);
    public String saveProduct(Product product);
    public String updateProduct(Integer id, Product product);
    public String deleteProduct(Integer id);
}
