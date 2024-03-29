package com.stock.Service.implementation;

import com.stock.Enum.StockType;
import com.stock.Model.Stock;
import com.stock.Repository.StockRepository;
import com.stock.Service.StockService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepository stockRepository;
    @PostConstruct
    public void init(){
        Optional<Stock> opt = stockRepository.findById(1);
        if(opt.isEmpty()){
            Stock s1 = new Stock();
            s1.setStockName("Tata");
            s1.setQuantity(10);
            s1.setListingPrice(100);
            s1.setPricePerUnit(10);
            s1.setStockType(StockType.COMMON);
            stockRepository.save(s1);

            Stock s2 = new Stock();
            s2.setStockName("Tcs");
            s2.setQuantity(10);
            s2.setListingPrice(50);
            s2.setPricePerUnit(5);
            s2.setStockType(StockType.COMMON);
            stockRepository.save(s2);

            Stock s3 = new Stock();
            s3.setStockName("Reliance");
            s3.setQuantity(10);
            s3.setListingPrice(200);
            s3.setPricePerUnit(20);
            s3.setStockType(StockType.PREFERRED);
            stockRepository.save(s3);

            Stock s4 = new Stock();
            s4.setStockName("Hdfc");
            s4.setQuantity(10);
            s4.setListingPrice(150);
            s4.setPricePerUnit(15);
            s4.setStockType(StockType.PREFERRED);
            stockRepository.save(s4);

        }
    }

}
