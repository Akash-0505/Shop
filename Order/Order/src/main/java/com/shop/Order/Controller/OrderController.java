
package com.shop.Order.Controller;

import com.shop.Order.Openfign.ProductClint;
import com.shop.Order.RequstCopy.Product;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/Order")
@AllArgsConstructor
public class OrderController {

    private final ProductClint productClint;

//OpenFeing
    @GetMapping("/get")
    public ResponseEntity<Page<Product>> getProduct(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize, Product product){

        return productClint.getProduct(pageNo,pageSize,product);
    }

    @GetMapping("/")
    public ResponseEntity<Product> getProductById(@RequestParam Integer id){
        return productClint.getProductById(id);
    }


    @PostMapping("/save")
    public String saveProduct(@RequestBody Product product){
        return productClint.saveProduct(product);
    }


    @PutMapping("/update")
    public String updateProduct(@RequestParam Integer id,@RequestBody Product product){
        return productClint.updateProduct(id, product);
    }


    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam Integer id){
        return productClint.deleteProduct(id);
    }

}

