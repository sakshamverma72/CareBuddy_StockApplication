package com.stock.Model;

import com.stock.Enum.StockType;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stockId;
    private String stockName;
    @Enumerated(EnumType.STRING)
    private StockType stockType;
    private Integer quantity;
    private Integer listingPrice;
    private Integer pricePerUnit;

}
