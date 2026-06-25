package com.marco.crudapi.Product.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.marco.crudapi.Product.Dto.ProductRequest;
import com.marco.crudapi.Product.Entity.Product;
import com.marco.crudapi.Product.Service.ProductService;

import io.swagger.v3.oas.annotations.Operation;

import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/products")
public class ProductController{

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping()
    @Operation(
        summary = "Get all products.", 
        description = "Returns a list of all products.."
    )
    public ResponseEntity< List<Product> > getProducts() {
        List<Product> products =service.getAll();

        return ResponseEntity.ok(products);
  
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @Operation(
        summary = "Get a product.", 
        description = "Returns a product by its ID."
    )
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Optional<Product> product =service.getProductById(id);

        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }

        return ResponseEntity.notFound().build();
    }

    @CrossOrigin
    @PostMapping()
    @Operation(
        summary = "Create a product.", 
        description = "Creates a new product."
    )
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest product) {
        Product saveProduct = service.createProduct(product);

        return ResponseEntity.status(201).body(saveProduct);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    @Operation(
        summary = "Update a product.", 
        description = "Updates an existing product."
    )
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductRequest product) {
        
        Optional<Product> productUp = service.updateProduct(id,product);

        if(productUp.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productUp.get());
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete a product.", 
        description = "Deletes a product by its ID."
    )
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        if(service.deleteProduct(id) == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
    
    
    
    

}