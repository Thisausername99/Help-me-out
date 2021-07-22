package com.international.codyweb.models;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "verificationtoken")
public class VerificationToken extends AuditModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="token")
    private String token;


    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    public VerificationToken() {
    	super();
    }
    
    public VerificationToken(User user) {
        this.user = user;
        token = UUID.randomUUID().toString();
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
