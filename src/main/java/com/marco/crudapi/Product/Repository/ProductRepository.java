package com.marco.crudapi.Product.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marco.crudapi.Product.Entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}