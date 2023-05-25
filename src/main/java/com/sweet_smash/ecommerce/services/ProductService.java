package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.dtos.CreateProductDto;
import com.sweet_smash.ecommerce.entities.Product;
import com.sweet_smash.ecommerce.entities.Stock;
import com.sweet_smash.ecommerce.repositories.ProductRepository;
import com.sweet_smash.ecommerce.repositories.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final StockRepository stockRepository;

    public ProductService(ProductRepository productRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }


    public Product createProduct(CreateProductDto createProductDto) {

        List<Stock> stocks = stockRepository.findAll();

        System.out.println(stocks);

        Stock stock = stockRepository.findByName(createProductDto.getStockName()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock name not found")
        );

        Product product = new Product(
                createProductDto.getName(),
                createProductDto.getLabel(),
                createProductDto.getDescription(),
                createProductDto.getUnitPrice(),
                createProductDto.getImages(),
                stock
        );

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product getProductDetailById(long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found")
        );
    }
}
