package com.mkiats.commons.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "watchlists")
public class Watchlist {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false, unique = true)
	private String id;

	@Column(name = "watchlist_name")
	private String watchlistName;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY,
		mappedBy = "watchlist"
	)
	private List<Watchlist_tickers> watchlistTickers = new ArrayList<>();
}
