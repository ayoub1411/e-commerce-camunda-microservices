package com.products;




import com.products.dao.CategoryRepository;
import com.products.dao.ProductRepository;
import com.products.entities.Category;
import com.products.entities.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {


        private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public DataLoader(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        // Create categories
        Category electronics = new Category("Electronics");
        Category books = new Category("Books");
        Category clothing = new Category("Clothing");

        categoryRepository.save(electronics);
        categoryRepository.save(books);
        categoryRepository.save(clothing);

        // Create products
        productRepository.save(new Product("Laptop", 1200.00, electronics));
        productRepository.save(new Product("Smartphone", 800.00, electronics));
        productRepository.save(new Product("Headphones", 150.00, electronics));

        productRepository.save(new Product("Java Programming", 40.00, books));
        productRepository.save(new Product("Spring Boot Guide", 35.00, books));

        productRepository.save(new Product("T-Shirt", 20.00, clothing));
    }
}
