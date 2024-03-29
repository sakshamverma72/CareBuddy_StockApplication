package com.stock.Service;

import com.stock.Model.OrderMaster;
import com.stock.Model.Stock;
import com.stock.Model.TradeDetails;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public interface OrderMasterService {

    public OrderMaster signUp(String orderMasterName);
    public List<Stock> showAllStock();

    public List<TradeDetails> getHistory(Integer orderMasterId);

    public String buyTrade(Integer stockMasterId, String stockName, Integer quantity);

    public String sellTrade(Integer orderMasterId, String stockName, Integer quantity);

    public String updateTrade(Integer orderMasterId, String stockName, Integer quantity);

}
