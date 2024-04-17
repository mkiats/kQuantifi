package com.mkiats.dao;

import com.mkiats.entity.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, String> {

    Optional<Ticker> findByTickerSymbol(String theTickerSymbol);
}
