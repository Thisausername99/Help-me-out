package com.international.authoriziation.server.token.pojo;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.international.authoriziation.server.model.entity.UserEntity;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Table(name = "verificationtoken")

@Getter @Setter @NoArgsConstructor
public class VerificationToken {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="token")
    private String token;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp timeStamp;
    
    @Column(updatable = false)
    @Basic(optional = false)
    private LocalDateTime expireAt;
    

    @OneToOne(targetEntity = UserEntity.class) 
    		//fetch = FetchType.EAGER)
    		//, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private UserEntity user;

    @Transient
    private boolean isExpired;
    
    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }	
}
