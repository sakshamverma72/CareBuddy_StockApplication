package com.stock.Repository;

import com.stock.Model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Integer> {
}
