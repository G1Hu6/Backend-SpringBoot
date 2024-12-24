package com.orm.controllers;

import com.orm.entities.Product;
import com.orm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.AttributedCharacterIterator;
import java.util.List;

@RestController
@RequestMapping(path = "products")
public class ProductController {

    @Autowired
    ProductService productService;

//    @GetMapping
//    public List<Product> getAllProductsByPrice(){
//        return productService.findByOrderByPrice();
//    }

    @GetMapping(path = "/filtered")
    public List<Product> getAllProductsByPriceAndName(@RequestParam(defaultValue = "") String name) {
        return productService.findByNameOrderByPrice(name);
    }

    @GetMapping(path = "/find")
    public List<Product> getAllSortedProducts(@RequestParam(defaultValue = "id") String sortBy) {
        return productService.findBy(sortBy);
    }

//    @GetMapping
//    public Page<Product> getAllProductsByPage(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "0") Integer pageNumber){
//        return productService.findAllProductsByPages(pageNumber,sortBy);
//    }

    @GetMapping
    public List<Product> getAllProductsByPage(@RequestParam(defaultValue = "id") String sortBy, @RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "") String name){
        return productService.findAllPagesWithNameIgnoreCase( pageNumber, sortBy, name);
    }
}