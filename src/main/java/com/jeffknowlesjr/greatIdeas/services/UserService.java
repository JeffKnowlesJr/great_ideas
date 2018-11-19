package com.jeffknowlesjr.greatIdeas.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.jeffknowlesjr.greatIdeas.models.User;
import com.jeffknowlesjr.greatIdeas.repositories.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService( UserRepository userRepository ) {
		this.userRepository = userRepository;
	}
	
	public User create( User user ) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
	return userRepository.save( user );
	}
	
	public Boolean isPass( User user, String password ) {
		return BCrypt.checkpw(password, user.getPassword());
	}
	
	public ArrayList<User> findAll() {
		return (ArrayList<User>) userRepository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> user = userRepository.findById( id );
		if ( user.isPresent() ) {
		return user.get();
		} else {
		return null;
		}
	}
	
	public User findByEmail(String email) {
		Optional<User> user = userRepository.findByEmail( email );
		if ( user.isPresent() ) {
		return user.get();
		} else {
		return null;
		}
	}
	
	public User findByName(String name) {
		Optional<User> user = userRepository.findByName( name );
		if ( user.isPresent() ) {
		return user.get();
		} else {
		return null;
		}
	}

	public User update( User user ) {
		return userRepository.save( user );
	}
	
	public void destroy( User user ) {
		userRepository.delete( user );
	}
	
	public void destroyById( Long id ) {
		userRepository.deleteById( id );
	}
	
}
