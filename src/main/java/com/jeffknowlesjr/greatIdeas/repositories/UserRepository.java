package com.jeffknowlesjr.greatIdeas.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeffknowlesjr.greatIdeas.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findById( Long id );
	Optional<User> findByEmail( String email);
	Optional<User> findByName( String name);
}