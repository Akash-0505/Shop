package com.shop.Order.Openfign;

import com.shop.Order.RequstCopy.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "Product", url = "http://localhost:8081/product")

public interface ProductClint {

    @GetMapping("/get")
    public ResponseEntity<Page<Product>> getProduct(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize, @SpringQueryMap Product product);
    @GetMapping("/")
    public ResponseEntity<String> getProductById(@RequestParam Integer id);
    @PostMapping("/save")
    public String saveProduct(@RequestBody Product product);
    @PutMapping("/update")
    public String updateProduct(@RequestParam Integer id,@RequestBody Product product);
    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam Integer id);
}
