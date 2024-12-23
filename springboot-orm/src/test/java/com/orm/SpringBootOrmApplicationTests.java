package com.orm;

import com.orm.entities.Product;
import com.orm.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class SpringBootOrmApplicationTests {

	@Autowired
	ProductRepository productRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void testInsertData(){
		/* Lombok error
		Product product = Product.builder()
				.sku("keyboard12E")
				.price(BigInteger.valueOf(2000))
				.name("Keyboard")
				.quantity(4)
				.build();
		*/
		Product product = new Product();
		product.setSku("lakme");
		product.setQuantity(1);
		product.setName("lakmesa");
		product.setPrice(BigInteger.valueOf(2344));
		System.out.println(productRepo.save(product));
	}

	@Test
	void testGetAllProducts(){
		List<Product> allProducts = productRepo.findAll();
		System.out.println(allProducts);
	}

	@Test
	void testCustomQueryMethods(){
		List<Product> filteredProducts0 = productRepo.findByName("ParleG");
		System.out.println(filteredProducts0);

		List<Product> filteredProducts1 = productRepo.findByCreatedAtAfter(LocalDateTime.of(2024,12,24,0,0,0));
		System.out.println(filteredProducts1);

		List<Product> filteredProducts2 = productRepo.findByQuantityAndPrice(4, BigInteger.valueOf(34));
		System.out.println(filteredProducts2);

		List<Product> filteredProducts3 = productRepo.findByNameLike("%sa%");
		System.out.println(filteredProducts3);

		List<Product> filteredProducts4 = productRepo.findByNameContains("sa");
		System.out.println(filteredProducts4);

		List<Product> filteredProducts5 = productRepo.findByNameContainsIgnoreCase("MaNg");
		System.out.println(filteredProducts5);

		Optional<Product> selectedProduct0 = productRepo.findByNameAndPrice("Mango",BigInteger.valueOf(34));
		System.out.println(selectedProduct0);
		selectedProduct0.ifPresent(System.out::println);

		List<Optional<Product>> selectedProducts = productRepo.findByNameOrPrice("ParleG",BigInteger.valueOf(34));
		System.out.println(selectedProducts);

		List<Product> filterProducts6 = productRepo.findDistinctByName("ParleG");
		System.out.println(filterProducts6);
	}
}
