package com.mkiats.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "watchlist_tickers")
@Entity
public class Watchlist_tickers {

	@Id
	@Column(name = "id", updatable = false, unique = true)
	private String id;

	@ManyToOne
	@JoinColumn(name = "ticker_id")
	private Ticker ticker;

	@ManyToOne
	@JoinColumn(name = "watchlist_id")
	private Watchlist watchlist;
}
