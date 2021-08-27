package com.international.authoriziation.server.auth;

import java.util.*;
import java.util.function.Function;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;


import org.springframework.util.Assert;


public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonIgnore
	private String password;

	private final String username;

	private Collection<? extends GrantedAuthority> authorities;

	private final boolean accountNonExpired;

	private final boolean accountNonLocked;

	private final boolean credentialsNonExpired;

	private final boolean enabled;


	//	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(Long id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this(id, username, password, true, true, true, true, authorities);
	}


	//Constructor for MemberDetail to build auth object

	public UserDetailsImpl(Long id, String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {

		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException(
					"Cannot pass null or empty values to constructor");
		}

		this.id = id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.accountNonExpired = accountNonExpired;
		this.credentialsNonExpired = credentialsNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.authorities = null;
	}


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public Long getId() {
		return id;
	}
	

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void eraseCredentials() {
		password = null;
	}

	
	@Override
	public boolean equals(Object o) {
		if (o instanceof UserDetailsImpl) {
			return username.equals(((UserDetailsImpl) o).username);
		}
		return false;
	}



	public static UserDetailsImpl.UserBuilder withUsername(String username) {
		return builder().username(username);
	}

	public static UserDetailsImpl.UserBuilder builder() {
		return new UserDetailsImpl.UserBuilder();
	}

	public static UserDetailsImpl.UserBuilder withUserDetails(UserDetails userDetails) {
		return withUsername(userDetails.getUsername())
				.password(userDetails.getPassword())
				.accountExpired(!userDetails.isAccountNonExpired())
				.accountLocked(!userDetails.isAccountNonLocked())
				.authorities(userDetails.getAuthorities())
				.credentialsExpired(!userDetails.isCredentialsNonExpired())
				.disabled(!userDetails.isEnabled());
	}

	public static class UserBuilder {
		private Long id;
		private String username;
		private String password;
		private List<GrantedAuthority> authorities;
		private boolean accountExpired;
		private boolean accountLocked;
		private boolean credentialsExpired;
		private boolean disabled;
		private Function<String, String> passwordEncoder = password -> password;

		/**
		 * Creates a new instance
		 */
		private UserBuilder() {
		}



		/**
		 * Populates the username. This attribute is required.
		 *
		 * @param username the username. Cannot be null.
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder username(String username) {
			Assert.notNull(username, "username cannot be null");
			this.username = username;
			return this;
		}


		/**
		 * Populates the id. This attribute is required.
		 *
		 * @param user id.
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder id(Long id) {
			Assert.notNull(id, "id cannot be null");
			this.id = id;
			return this;
		}



		/**
		 * Populates the password. This attribute is required.
		 *
		 * @param password the password. Cannot be null.
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder password(String password) {
			Assert.notNull(password, "password cannot be null");
			this.password = password;
			return this;
		}

		/**
		 * Encodes the current password (if non-null) and any future passwords supplied
		 * to {@link #password(String)}.
		 *
		 * @param encoder the encoder to use
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder passwordEncoder(Function<String, String> encoder) {
			Assert.notNull(encoder, "encoder cannot be null");
			this.passwordEncoder = encoder;
			return this;
		}

		/**
		 * Populates the roles. This method is a shortcut for calling
		 * {@link #authorities(String...)}, but automatically prefixes each entry with
		 * "ROLE_". This means the following:
		 *
		 * <code>
		 *     builder.roles("USER","ADMIN");
		 * </code>
		 *
		 * is equivalent to
		 *
		 * <code>
		 *     builder.authorities("ROLE_USER","ROLE_ADMIN");
		 * </code>
		 *
		 * <p>
		 * This attribute is required, but can also be populated with
		 * {@link #authorities(String...)}.
		 * </p>
		 *
		 * @param roles the roles for this user (i.e. USER, ADMIN, etc). Cannot be null,
		 * contain null values or start with "ROLE_"
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder roles(String... roles) {
			List<GrantedAuthority> authorities = new ArrayList<>(
					roles.length);
			for (String role : roles) {
				Assert.isTrue(!role.startsWith("ROLE_"), () -> role
						+ " cannot start with ROLE_ (it is automatically added)");
				authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
			}
			return authorities(authorities);
		}





		/**
		 * Populates the authorities. This attribute is required.
		 *
		 * @param authorities the authorities for this user. Cannot be null, or contain
		 * null values
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 * @see #roles(String...)
		 */
		public UserDetailsImpl.UserBuilder authorities(GrantedAuthority... authorities) {
			return authorities(Arrays.asList(authorities));
		}

		/**
		 * Populates the authorities. This attribute is required.
		 *
		 * @param authorities the authorities for this user. Cannot be null, or contain
		 * null values
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 * @see #roles(String...)
		 */
		public  UserDetailsImpl.UserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
			this.authorities = new ArrayList<>(authorities);
			return this;
		}



		/**
		 * Populates the authorities. This attribute is required.
		 *
		 * @param authorities the authorities for this user (i.e. ROLE_USER, ROLE_ADMIN,
		 * etc). Cannot be null, or contain null values
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 * @see #roles(String...)
		 */
		public UserDetailsImpl.UserBuilder authorities(String... authorities) {
			return authorities(AuthorityUtils.createAuthorityList(authorities));
		}




		/**
		 * Defines if the account is expired or not. Default is false.
		 *
		 * @param accountExpired true if the account is expired, false otherwise
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder accountExpired(boolean accountExpired) {
			this.accountExpired = accountExpired;
			return this;
		}

		/**
		 * Defines if the account is locked or not. Default is false.
		 *
		 * @param accountLocked true if the account is locked, false otherwise
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder accountLocked(boolean accountLocked) {
			this.accountLocked = accountLocked;
			return this;
		}

		/**
		 * Defines if the credentials are expired or not. Default is false.
		 *
		 * @param credentialsExpired true if the credentials are expired, false otherwise
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder credentialsExpired(boolean credentialsExpired) {
			this.credentialsExpired = credentialsExpired;
			return this;
		}

		/**
		 * Defines if the account is disabled or not. Default is false.
		 *
		 * @param disabled true if the account is disabled, false otherwise
		 * @return the {@link User.UserBuilder} for method chaining (i.e. to populate
		 * additional attributes for this user)
		 */
		public UserDetailsImpl.UserBuilder disabled(boolean disabled) {
			this.disabled = disabled;
			return this;
		}


		public UserDetails build() {
			String encodedPassword = this.passwordEncoder.apply(password);
			return new UserDetailsImpl(id, username, encodedPassword, !disabled, !accountExpired,
					!credentialsExpired, !accountLocked, authorities);
		}
	}










}