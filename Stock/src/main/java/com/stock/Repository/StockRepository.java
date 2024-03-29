package com.stock.Repository;

import com.stock.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
    public Optional<Stock> findByStockName(String stockName);
}
