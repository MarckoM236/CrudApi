package com.marco.crudapi.Product.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.marco.crudapi.Product.Entity.Product;
import com.marco.crudapi.Product.Repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    //all products
    public List<Product> getAll() {
        return repository.findAll();
    }

    //product By  id
    public Optional<Product> getProductById(Long id){

        Optional<Product> product = repository.findById(id);

        return product;
    }

    //create product
    public Product createProduct(Product product){
        Product productSave = repository.save(product);

        return productSave;
    }

    //update product
    public Optional<Product> updateProduct(Long id,Product product){
        if(!repository.existsById(id)){
            return Optional.empty();
        }

        product.setId(id);

        Product productUp = repository.save(product);

        return Optional.of(productUp);
    }

    //delete product
    public Boolean deleteProduct(Long id){
        if(!repository.existsById(id)){
            return null;
        }

        repository.deleteById(id);

        return true;

    }
    
}
