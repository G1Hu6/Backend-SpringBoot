package com.orm.services;

import com.orm.entities.Product;
import com.orm.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    private final Integer PAGE_SIZE = 5; // Default page size

    public List<Product> findByNameOrderByPrice(String name){
        return productRepository.findByNameOrderByPrice(name);
    }

    public List<Product> findByOrderByPrice(){
        return productRepository.findByOrderByPrice();
    }


    public List<Product> findBy(String sortBy){
        //return productRepository.findBy(Sort.by(Sort.Direction.ASC,sortBy));
        //return productRepository.findBy(Sort.by(Sort.Direction.ASC,sortBy,"price"));
        //return productRepository.findBy(Sort.by(Sort.Direction.ASC,sortBy,"price","name"));
        return productRepository.findBy(Sort.by(Sort.Order.asc(sortBy),Sort.Order.desc("price")));
    }

    // Return Page having 5 elements
    public Page<Product> findAllProductsByPages(Integer pageNumber, String sortBy){

        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.ASC,sortBy));

        return productRepository.findAll(pageable);
    }

    // Return Page having 5 elements as List without extra pageable values
    public List<Product> findAllProductsByPagesAsList(Integer pageNumber, String sortBy){

        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(Sort.Direction.ASC,sortBy));

        return productRepository.findAll(pageable).getContent();
    }

    public List<Product> findAllPagesWithNameIgnoreCase(Integer pageNumber, String sortBy, String name){
        return productRepository.findByNameContainsIgnoreCase(
                name,
                PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy))
        );
    }

}
