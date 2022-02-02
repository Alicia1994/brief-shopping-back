package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	public Role() {

	}
/*
	@OneToMany(orphanRemoval = true, mappedBy = "role", cascade = CascadeType.ALL)
	private Set<User> users ;*/

}