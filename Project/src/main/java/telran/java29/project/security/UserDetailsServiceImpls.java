package telran.java29.project.security;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import telran.java29.project.dao.UserRepository;

@Service
public class UserDetailsServiceImpls implements UserDetailsService{
	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		telran.java29.project.domain.User userAccount = repository.findById(username)
				.orElseThrow(()->new UsernameNotFoundException(username));
		String password = userAccount.getPassword();
		Set<String> setRoles = userAccount.getRoles();

		return new User(username, password, 
				AuthorityUtils.createAuthorityList(setRoles.toArray(new String[setRoles.size()])));
	}
	
	
}
