package com.mkiats.commons.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticker")
@Entity
public class Ticker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private String id;

	@Column(name = "ticker_name")
	private String tickerName;

	@Column(name = "ticker_symbol")
	private String tickerSymbol;

	@Column(name = "ticker_priceInfo_id")
	private String tickerPriceInfo;

	@OneToMany(
		mappedBy = "ticker",
		fetch = FetchType.LAZY,
		cascade = CascadeType.ALL
	)
	private List<Watchlist_tickers> watchlistTickers = new ArrayList<
		Watchlist_tickers
	>();

	@OneToMany(
		mappedBy = "ticker",
		fetch = FetchType.LAZY,
		cascade = CascadeType.ALL
	)
	private List<TickerPriceInfo> tickerPriceInfos = new ArrayList<>();
}
