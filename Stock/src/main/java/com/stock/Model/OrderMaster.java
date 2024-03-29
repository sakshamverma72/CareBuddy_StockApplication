package com.stock.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class OrderMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderMasterId;
    private String orderMasterName;
    @OneToMany(mappedBy = "orderMaster")
    private List<TradeDetails> tradeDetailsList = new ArrayList<>();
}
