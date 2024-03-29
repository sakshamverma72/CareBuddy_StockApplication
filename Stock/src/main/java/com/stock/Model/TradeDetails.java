package com.stock.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.stock.Enum.StockType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class TradeDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tradeId;
    private LocalDateTime tradeDateTime;
    private String stockName;
    private Integer listingPrice;
    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private StockType stockType;
    private Integer pricePerUnit;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderMaster orderMaster;
}
