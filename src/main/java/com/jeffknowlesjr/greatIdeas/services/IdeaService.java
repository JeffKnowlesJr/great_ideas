package com.jeffknowlesjr.greatIdeas.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jeffknowlesjr.greatIdeas.models.Idea;
import com.jeffknowlesjr.greatIdeas.repositories.IdeaRepository;

@Service
public class IdeaService {
	
	private IdeaRepository ideaRepository;
	
	public IdeaService( IdeaRepository ideaRepository ) {
		this.ideaRepository = ideaRepository;
	}

	public Idea create( Idea idea ) {
		return ideaRepository.save( idea );
	}

	public ArrayList<Idea> findAll() {
		return (ArrayList<Idea>) ideaRepository.findAll();
	}

	public Idea findById(Long id) {
		Optional<Idea> idea = ideaRepository.findById( id );
		if ( idea.isPresent() ) {
			return idea.get();
		} else {
			return null;
		}
	}


	public ArrayList<Idea> sortByLeast() {
		
		return null;
	}
	
	public Idea findByName(String name) {
		Optional<Idea> idea = ideaRepository.findByName( name );
		if ( idea.isPresent() ) {
			return idea.get();
		} else {
			return null;
		}
	}

	public ArrayList<Idea> findHigh() {
		return null;
		
		
	}
	
	public ArrayList<Idea> findLow() {
		return null;
		
	}

	public Idea update( Idea idea ) {
		return ideaRepository.save( idea );
	}

	public void destroy( Idea idea ) {
		ideaRepository.delete( idea );
	}

	public void destroyById( Long id ) {
		ideaRepository.deleteById( id );
	}
}
