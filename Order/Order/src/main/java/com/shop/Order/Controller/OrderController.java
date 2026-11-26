
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

    private final RestTemplate restTemplate;
    private final WebClient webClient;
    private final ProductClint productClint;


    // REST TEMPLATE

    @PostMapping("/resttemplate/create")
    public ResponseEntity<String> create(
            @RequestBody Product product) {

        ResponseEntity<String> response =
                restTemplate.postForEntity(
                        "http://localhost:8081/product/save",
                        product,
                        String.class
                );

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response.getBody());
    }

    @GetMapping("/resttemplate/get")
    public ResponseEntity<String> getProducts(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "http://localhost:8081/product/get?pageNo={pageNo}&pageSize={pageSize}",
                        HttpMethod.GET,
                        null,
                        String.class,
                        pageNo,
                        pageSize
                );

        return ResponseEntity.ok(response.getBody());
    }

    @GetMapping("/resttemplate/{id}")
    public ResponseEntity<String> getProduct(
            @PathVariable Integer id) {

        ResponseEntity<String> response =
                restTemplate.getForEntity(
                        "http://localhost:8081/product/?id={id}",
                        String.class,
                        id
                );

        return ResponseEntity.ok(response.getBody());
    }

    @PutMapping("/resttemplate/update/{id}")
    public ResponseEntity<String> update(
            @PathVariable Integer id,
            @RequestBody Product product) {

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "http://localhost:8081/product/update?id={id}",
                        HttpMethod.PUT,
                        new HttpEntity<>(product),
                        String.class,
                        id
                );

        return ResponseEntity.ok(response.getBody());
    }

    @DeleteMapping("/resttemplate/delete/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Integer id) {

        ResponseEntity<String> response =
                restTemplate.exchange(
                        "http://localhost:8081/product/delete?id={id}",
                        HttpMethod.DELETE,
                        null,
                        String.class,
                        id
                );

        return ResponseEntity.ok(response.getBody());
    }


    // WEB CLIENT

    @PostMapping("/webclient/create")
    public Mono<ResponseEntity<String>> createWebClient(
            @RequestBody Product product) {

        return webClient.post()
                .uri("/product/save")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(String.class)
                .map(body -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(body));
    }

    @GetMapping("/webclient/get")
    public Mono<ResponseEntity<String>> getWebClient(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize) {

        return webClient.get()
                .uri("/product/get?pageNo={pageNo}&pageSize={pageSize}",
                        pageNo, pageSize)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/webclient/{id}")
    public Mono<ResponseEntity<String>> getByIdWebClient(
            @PathVariable Integer id) {

        return webClient.get()
                .uri("/product/?id={id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/webclient/update/{id}")
    public Mono<ResponseEntity<String>> updateWebClient(
            @PathVariable Integer id,
            @RequestBody Product product) {

        return webClient.put()
                .uri("/product/update?id={id}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(product)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }

    @DeleteMapping("/webclient/delete/{id}")
    public Mono<ResponseEntity<String>> deleteWebClient(
            @PathVariable Integer id) {

        return webClient.delete()
                .uri("/product/delete?id={id}", id)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok);
    }



//OpenFeing
    @GetMapping("/get")
    public ResponseEntity<Page<Product>> getProduct(@RequestParam(defaultValue = "0") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize, Product product){

        return productClint.getProduct(pageNo,pageSize,product);
    }

    @GetMapping("/")
    public ResponseEntity<String> getProductById(@RequestParam Integer id){
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
