package com.international.authoriziation.server.role;

//import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.international.authoriziation.server.model.entity.UserEntity;

import lombok.*;




@Entity
@Table(name = "roles")
@Getter @Setter @NoArgsConstructor
public class Role {
//	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private ERole name;

	@ManyToMany(mappedBy = "roles")
	@JsonBackReference
    private Set <UserEntity> users;
	
	
	public Role(ERole name) {
		this.name = name;
	}

}