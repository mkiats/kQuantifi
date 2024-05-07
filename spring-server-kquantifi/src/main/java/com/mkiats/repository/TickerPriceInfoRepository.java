package com.mkiats.repository;

import com.mkiats.entities.TickerPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerPriceInfoRepository
	extends JpaRepository<TickerPriceInfo, String> {}
