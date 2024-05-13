package com.mkiats.commons.repository;

import com.mkiats.commons.entities.TickerPriceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerPriceInfoRepository
	extends JpaRepository<TickerPriceInfo, String> {}
