package com.stock.Controller;

import com.stock.Model.OrderMaster;
import com.stock.Model.Stock;
import com.stock.Model.TradeDetails;
import com.stock.Service.OrderMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/trader")
@CrossOrigin("*")
public class TradeController {

    @Autowired
    private OrderMasterService orderMasterService;

    @PostMapping("/signup/{orderMasterName}")
    public ResponseEntity<OrderMaster> signUp(@PathVariable String orderMasterName) {
        return new ResponseEntity<>(orderMasterService.signUp(orderMasterName), HttpStatus.CREATED);
    }

    @GetMapping("/showAllStock")
    public ResponseEntity<List<Stock>> showAllStock(){
        return new ResponseEntity<>(orderMasterService.showAllStock(), HttpStatus.OK);
    }

    @GetMapping("/getHistory/{orderMasterId}")
    public ResponseEntity<List<TradeDetails>> getHistory(@PathVariable Integer orderMasterId){
        return new ResponseEntity<>(orderMasterService.getHistory(orderMasterId), HttpStatus.OK);
    }

    @PostMapping("/buy/{stockMasterId}/{stockName}/{quantity}")
    public ResponseEntity<String> buyTrade(@PathVariable Integer stockMasterId, @PathVariable  String stockName,@PathVariable Integer quantity) {
        return new ResponseEntity<>(orderMasterService.buyTrade(stockMasterId, stockName, quantity), HttpStatus.CREATED);
    }

    @DeleteMapping("/sell/{stockMasterId}/{stockName}/{quantity}")
    public ResponseEntity<String> deleteTrade(@PathVariable Integer stockMasterId, @PathVariable  String stockName,@PathVariable Integer quantity) {
        return ResponseEntity.ok(orderMasterService.sellTrade(stockMasterId, stockName, quantity));
    }

    @PatchMapping("/update/{stockMasterId}/{stockName}/{quantity}")
    public ResponseEntity<String> updateTrade(@PathVariable Integer stockMasterId, @PathVariable  String stockName,@PathVariable Integer quantity) {
        return ResponseEntity.ok(orderMasterService.updateTrade(stockMasterId, stockName, quantity));
    }
}
