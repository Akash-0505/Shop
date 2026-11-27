package com.shop.Product.Service;

import com.shop.Product.Entity.Product;
import com.shop.Product.Repositry.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpliment implements ProductService{

    private ProductRepo productRepo;

    @Override
    public ResponseEntity<Page<Product>> getProduct(Integer pageNo, Integer pageSize, Product product) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<Product> products = productRepo.findAll(pageable);

        return ResponseEntity.ok(products);
    }

    @Override
    public ResponseEntity<Product> getProductById(Integer id) {
        Optional<Product> optionalProduct =productRepo.findById(id);
            Product product=optionalProduct.get();
            return ResponseEntity.ok(product);

    }

    @Override
    public String saveProduct(Product product) {
        productRepo.save(product);
        return "Product Saved Successfully...";
    }

    @Override
    public String updateProduct(Integer id, Product product) {

        Optional<Product> optionalProduct =productRepo.findById(id);
        if(optionalProduct.isPresent()){
            Product product1=optionalProduct.get();
            product1.setProductName(product.getProductName());
            product1.setPrice(product.getPrice());
            product1.setCategory(product.getCategory());
            productRepo.save(product1);

            return "Product Updated Successfully...";
        }
        return "Invalid Id..!, Product Not Found..!" ;
    }

    @Override
    public String deleteProduct(Integer id) {
        Optional<Product> optionalProduct =productRepo.findById(id);
        if(optionalProduct.isPresent()){
            productRepo.deleteById(id);
            return "Product Removed Successfully...";
        }
        return "Invalid Id..!";
    }
}
