package com.international.codyweb.core.user.model;

//import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.international.codyweb.core.user.ERole;

@Entity
@Table(name = "roles")
public class Role {
//	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	@ManyToMany(mappedBy = "roles")
    private Set <User> users;
	
	
	public Role() {

	}

	public Role(ERole name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ERole getName() {
		return name;
	}

	public void setName(ERole name) {
		this.name = name;
	}

	public Set <User> getUsers() {
		return users;
	}

	public void setUsers(Set <User> users) {
		this.users = users;
	}
}