package com.shop.Product.controller;

import com.shop.Product.Entity.Product;
import com.shop.Product.Service.ProductServiceImpliment;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
//@NoArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductServiceImpliment productServiceImpliment;

    @GetMapping("/get")
    public ResponseEntity<Page<Product>> getProduct(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize, Product product)
    {
        return productServiceImpliment.getProduct(pageNo,pageSize,product);
    }

    @GetMapping("/")
    public ResponseEntity<String> getProductById(@RequestParam Integer id)
    {
        return productServiceImpliment.getProductById(id);
    }

    @PostMapping("/save")
    public String saveProduct(@RequestBody Product product)
    {
        return productServiceImpliment.saveProduct(product);
    }

    @PutMapping("/update")
    public String updateProduct(@RequestParam Integer id,@RequestBody Product product)
    {
        return productServiceImpliment.updateProduct(id,product);
    }

    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam Integer id)
    {
        return productServiceImpliment.deleteProduct(id);
    }
}
