package com.example.Repository;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Paper;

public interface PaperRepository extends CrudRepository<Paper, Integer> {
	
}
