package com.international.authoriziation.server.model.entity;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import com.international.authoriziation.server.model.AuditModel;

import lombok.*;





@Entity
@Getter
@Setter
@Table(name = "friendship")
@NoArgsConstructor
@AllArgsConstructor
public class RelationshipEntity extends AuditModel{
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "first_user_id", referencedColumnName = "id")
    UserEntity firstUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "second_user_id", referencedColumnName = "id")
    UserEntity secondUser;
}
