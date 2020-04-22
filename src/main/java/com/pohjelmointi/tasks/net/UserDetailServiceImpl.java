package com.pohjelmointi.tasks.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pohjelmointi.tasks.domain.Kayttaja;
import com.pohjelmointi.tasks.domain.KayttajaRepository;

/**
 * This class is used by spring security to authenticate and authorize user
 **/
@Service
public class UserDetailServiceImpl implements UserDetailsService  {
	private final KayttajaRepository repository;

	@Autowired
	public UserDetailServiceImpl(KayttajaRepository userRepository) {
		this.repository = userRepository;
	}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {   
    	Kayttaja curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }   
}
