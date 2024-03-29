package com.stock.Service.implementation;

import com.stock.Enum.StockType;
import com.stock.Exceptions.TradeException;
import com.stock.Model.OrderMaster;
import com.stock.Model.Stock;
import com.stock.Model.TradeDetails;
import com.stock.Repository.OrderMasterRepository;
import com.stock.Repository.StockRepository;
import com.stock.Repository.TradeDetailRepository;
import com.stock.Service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private TradeDetailRepository tradeDetailRepository;

    @Override
    public OrderMaster signUp(String orderMasterName) {
        OrderMaster om = new OrderMaster();
        om.setOrderMasterName(orderMasterName);
        return orderMasterRepository.save(om);
    }

    @Override
    public List<Stock> showAllStock(){
        return stockRepository.findAll();
    }


    @Override
    public List<TradeDetails> getHistory(Integer orderMasterId){
        Optional<OrderMaster> opt = orderMasterRepository.findById(orderMasterId);

        OrderMaster om = opt.orElseThrow(() -> new NoSuchElementException("No order master found"));

        return om.getTradeDetailsList();
    }


    @Override
    public String buyTrade(Integer orderMasterId, String stockName, Integer quantity) {
        Optional<Stock> opt1 = stockRepository.findByStockName(stockName);
        Stock stock = opt1.orElseThrow(() -> new NoSuchElementException("There is no stock with given input"));

        Integer q = stock.getQuantity();

        if(q < quantity){
            throw new TradeException("Not enough stocks");
        }

        stock.setQuantity(stock.getQuantity()-quantity);

        stockRepository.save(stock);

        OrderMaster om = orderMasterRepository.findById(orderMasterId).orElseThrow(() -> new NoSuchElementException("No order master found with given id"));

        TradeDetails td = new TradeDetails();
        td.setStockName(stockName);
        td.setListingPrice(stock.getListingPrice());
        td.setQuantity(quantity);
        td.setStockType(stock.getStockType());
        td.setPricePerUnit(stock.getPricePerUnit());
        td.setTradeDateTime(LocalDateTime.now());
        td.setOrderMaster(om);

        tradeDetailRepository.save(td);

        List<TradeDetails> tDList = new ArrayList<>();
        tDList.add(td);

        om.setTradeDetailsList(tDList);

        orderMasterRepository.save(om);

        return "your " + quantity + " stock of " + stockName + " has been bought";
    }

    @Override
    public String sellTrade(Integer orderMasterId, String stockName, Integer quantity) {
        Optional<Stock> opt1 = stockRepository.findByStockName(stockName);
        Stock stock = opt1.orElseThrow(() -> new NoSuchElementException("There is no stock with given input"));

        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);

        OrderMaster om = orderMasterRepository.findById(orderMasterId).orElseThrow(() -> new NoSuchElementException("No order master found with given id"));

        List<TradeDetails> tradeDetailsList = new ArrayList<>(om.getTradeDetailsList());

        int i;
        for(i = 0; i < tradeDetailsList.size(); i++){
            TradeDetails tradeDetails = tradeDetailsList.get(i);

            if(tradeDetails.getStockName().equals(stockName)){
                if(tradeDetails.getQuantity() < quantity){
                    throw new TradeException("you can not sell more then you bought");
                }

                else{
                    TradeDetails td = tradeDetailRepository.findById(tradeDetails.getTradeId()).orElseThrow(() -> new NoSuchElementException("trade not found"));
                    td.setQuantity(td.getQuantity() -quantity);
                    tradeDetailRepository.save(td);
                    tradeDetailsList.set(i, td);
                    break;
                }
            }
//            else throw new TradeException("no any stock found with give input");
            if(i == tradeDetailsList.size()) throw new TradeException("no any stock found with give input");
        }

        om.setTradeDetailsList(tradeDetailsList);
        orderMasterRepository.save(om);

        return "Your stock has been sold";
    }

    @Override
    public String updateTrade(Integer orderMasterId, String stockName, Integer quantity) {
        Optional<Stock> opt1 = stockRepository.findByStockName(stockName);
        Stock stock = opt1.orElseThrow(() -> new NoSuchElementException("There is no stock with given input"));

        Integer q = stock.getQuantity();
//        Integer updatedStock = 0;

        if(q < quantity){
            throw new TradeException("Not enough stocks");
        }

        stock.setQuantity(stock.getQuantity()-quantity);

        stockRepository.save(stock);

        OrderMaster om = orderMasterRepository.findById(orderMasterId).orElseThrow(() -> new NoSuchElementException("No order master found with given id"));

        List<TradeDetails> tradeDetailsList = new ArrayList<>(om.getTradeDetailsList());

        int i;
        for(i = 0; i < tradeDetailsList.size(); i++) {
            TradeDetails tradeDetails = tradeDetailsList.get(i);

            if (tradeDetails.getStockName().equals(stockName)) {
                if (tradeDetails.getQuantity() <= 0) {
                    throw new TradeException("You need to buy a new trade");
                } else {
                    TradeDetails td = tradeDetailRepository.findById(tradeDetails.getTradeId()).orElseThrow(() -> new NoSuchElementException("trade not found"));
                    td.setQuantity(td.getQuantity() + quantity);
//                    updatedStock = td.getQuantity() + quantity;
                    tradeDetailRepository.save(td);
                    tradeDetailsList.set(i, td);
                    break;
                }
            }
//            else throw new TradeException("no any stock found with give input");
            if (i == tradeDetailsList.size()) throw new TradeException("no any stock found with give input");
        }

        om.setTradeDetailsList(tradeDetailsList);
        orderMasterRepository.save(om);

        return "your stock quantity of " + stockName + " has been updated by " + quantity;
    }


}
