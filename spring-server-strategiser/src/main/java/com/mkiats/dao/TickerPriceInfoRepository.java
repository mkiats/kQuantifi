package com.mkiats.dao;

import com.mkiats.entity.TickerPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerPriceInfoRepository extends JpaRepository<TickerPriceInfo, String> {
}
