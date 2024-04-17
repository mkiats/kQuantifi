package com.mkiats.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ticker_price_info")
@Entity
public class TickerPriceInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private String id;

	@Column(name = "timestamp")
	private String timestamp;

	@Column(name = "timeframe")
	private String timeframe;

	@Column(name = "open")
	private Float open;

	@Column(name = "high")
	private Float high;

	@Column(name = "low")
	private Float low;

	@Column(name = "close")
	private Float close;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticker_id")
	private Ticker ticker;
}
