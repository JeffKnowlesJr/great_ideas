package com.jeffknowlesjr.greatIdeas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jeffknowlesjr.greatIdeas.models.Idea;
import com.jeffknowlesjr.greatIdeas.models.User;

@Repository
public interface IdeaRepository extends CrudRepository<Idea, Long> {
	Optional<Idea> findByName( String name );
	Optional<Idea> findById( Long id );
}
