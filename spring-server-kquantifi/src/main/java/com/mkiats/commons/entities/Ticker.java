package com.mkiats.commons.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ticker")
@Entity
public class Ticker {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, unique = true)
	private Long id;

	@Column(name = "ticker_name")
	private String tickerName;

	@Column(name = "inception_date")
	private String inceptionDate;

	public Ticker(String tickerName, String earliestInceptionDate) {
		this.tickerName = tickerName;
		this.inceptionDate = earliestInceptionDate;
	}
}
