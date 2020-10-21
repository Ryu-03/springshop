package springshop.domain.secuity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import springshop.domain.User;
import springshop.domain.repository.UserRepository;

@Service
public class ShoppingUserDetailsService implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = userRepository.findByUserId(username);
		if(user == null) {
			throw new UsernameNotFoundException(username + "is not found");
		}		
		return new ShoppingUserDetails(user , getAuthorities(user));
	}
	
	private Collection<GrantedAuthority> getAuthorities (User user){
		return AuthorityUtils.createAuthorityList("ROLE_USER");
	}

}
