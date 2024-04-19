package com.mkiats.dataAccessObjects;

import com.mkiats.entities.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TickerRepository extends JpaRepository<Ticker, String> {

    Optional<Ticker> findByTickerSymbol(String theTickerSymbol);
}
