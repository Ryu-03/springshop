	package springshop.domain.secuity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import springshop.domain.User;

@SuppressWarnings("serial")
public class ShoppingUserDetails implements UserDetails{

	private final User user;
	private final Collection<GrantedAuthority> authorities;
	
	public ShoppingUserDetails(User user , Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	
	public User getUser() {
		return user;
	}
		
	@Override
	public String getPassword() {
		return this.user.getPassword();
	}
	
	@Override
	public String getUsername() {
		return this.user.getUserId();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
}
