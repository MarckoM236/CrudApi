package com.marco.crudapi.Product.Service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.marco.crudapi.Product.Entity.Product;
import com.marco.crudapi.Product.Repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(
            ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }
    
}
