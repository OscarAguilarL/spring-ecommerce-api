package com.sweet_smash.ecommerce.services;

import com.sweet_smash.ecommerce.entities.Stock;
import com.sweet_smash.ecommerce.repositories.StockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    public Stock getStockById(long stockId) {
        return this.stockRepository.findById(stockId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stock no encontrado")
        );
    }

    public void reduceStock(Stock newStock, int quantity) {
        Stock productStock = getStockById(newStock.getId());
        long newStockQuantity = newStock.getQuantity() - quantity;
        productStock.setQuantity(newStockQuantity);
        this.stockRepository.save(productStock);
    }
}
