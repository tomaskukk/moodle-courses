package com.example.moodle_courses.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.moodle_courses.domain.User;
import com.example.moodle_courses.domain.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserRepo repo;
	
	@Autowired
	public UserDetailServiceImpl(UserRepo repo) {
		this.repo = repo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws 
	UsernameNotFoundException {
		User curruser = repo.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(
				username, 
				curruser.getPasswordHash(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}
}
