package com.stock.Repository;

import com.stock.Model.TradeDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeDetailRepository extends JpaRepository<TradeDetails, Integer> {
}
