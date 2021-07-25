package com.international.codyweb.core.security.token.model;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import com.international.codyweb.core.user.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "verificationtoken")
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
    

    @OneToOne(targetEntity = User.class) 
    		//fetch = FetchType.EAGER)
    		//, cascade=CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName ="id")
    private User user;

    @Transient
    private boolean isExpired;
    
    public VerificationToken() {
    	super();
    }
    
    
    public LocalDateTime getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(LocalDateTime expireAt) {
        this.expireAt = expireAt;
    }

    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    public boolean isExpired() {
        return getExpireAt().isBefore(LocalDateTime.now()); // this is generic implementation, you can always make it timezone specific
    }
    

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}
	
}
