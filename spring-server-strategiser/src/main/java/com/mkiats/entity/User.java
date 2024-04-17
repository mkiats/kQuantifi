package com.mkiats.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", unique = true, updatable = false)
	private String id;

	@Column(name = "username")
	private String username;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "photo_url")
	private String photoUrl;

	@OneToMany(
		mappedBy = "user",
		cascade = CascadeType.ALL,
		fetch = FetchType.LAZY
	)
	private List<Watchlist> watchlists = new ArrayList<>();
}
