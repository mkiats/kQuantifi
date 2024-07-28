package com.mkiats.commons.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticker_price")
@Entity
public class TickerPrice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true)
	private Long id;

	@Column(name = "ticker_name")
	private String tickerName;

	@Column(name = "timeframe", nullable = false)
	private String timeframe;

	@Column(name = "timestamp", nullable = false)
	private String timestamp;

	@Column(name = "open")
	private Float open;

	@Column(name = "high")
	private Float high;

	@Column(name = "low")
	private Float low;

	@Column(name = "close")
	private Float close;

	@Column(name = "adjusted_close")
	private String adjustedClose;

	@Column(name = "volume")
	private String volume;

	@Column(name = "dividend")
	private String dividendAmount;

	public TickerPrice(
		String tickerName,
		String timeframe,
		String timestamp,
		String open,
		String high,
		String low,
		String close,
		String adjustedClose,
		String volume,
		String dividendAmount
	) {
		this.tickerName = tickerName;
		this.timeframe = timeframe;
		this.timestamp = timestamp;
		this.open = Float.parseFloat(open);
		this.high = Float.parseFloat(high);
		this.low = Float.parseFloat(low);
		this.close = Float.parseFloat(close);
		this.adjustedClose = adjustedClose;
		this.volume = volume;
		this.dividendAmount = dividendAmount;
	}
}
