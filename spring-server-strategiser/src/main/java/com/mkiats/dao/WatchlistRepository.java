package com.mkiats.dao;

import com.mkiats.entity.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchlistRepository extends JpaRepository<Watchlist, String> {
}
